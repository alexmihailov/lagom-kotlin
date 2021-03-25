package com.witcher.lagom.university.impl.persistence.students;

import com.google.common.collect.ImmutableMap;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.persistence.Query;
import org.taymyr.play.repository.infrastructure.persistence.DatabaseExecutionContext;
import org.taymyr.play.repository.infrastructure.persistence.JPARepository;
import play.db.jpa.JPAApi;

public class StudentsRepository extends JPARepository<StudentEntity, Integer> {

    @Inject
    public StudentsRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        super(jpaApi, executionContext, StudentEntity.class);
    }

    @Override
    public Integer nextIdentity() {
        throw new AbstractMethodError("this method shouldn't be used");
    }

    public CompletionStage<List<StudentEntity>> getStudents(
            @Nullable String firstName,
            @Nullable String middleName,
            @Nullable String lastName,
            @Nullable LocalDate birthday,
            @Nullable String group
    ) {
        String query = "select s from " + StudentEntity.class.getName() + " s where 1=1";
        ImmutableMap.Builder<String, Object> parameters = new ImmutableMap.Builder<>();
        if (firstName != null) {
            query += " and s.firstName = :firstName";
            parameters.put("firstName", firstName);
        }
        if (middleName != null) {
            query += " and s.middleName = :middleName";
            parameters.put("middleName", middleName);
        }
        if (lastName != null) {
            query += " and s.lastName = :lastName";
            parameters.put("lastName", lastName);
        }
        if (birthday != null) {
            query += " and s.birthday = :birthday";
            parameters.put("birthday", birthday);
        }
        if (group != null) {
            query += " and s.group = :group";
            parameters.put("group", group);
        }
        return findItemsByQuery(query, parameters.build(), StudentEntity.class);

    }

    @SuppressWarnings("unchecked")
    public <T> CompletionStage<List<T>> findItemsByQuery(String jpaQuery, ImmutableMap<String, Object> parameters, Class<T> clazz) {
        return this.executeRO(em -> {
            Query query = em.createQuery(jpaQuery);
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
            return query.getResultList();
        });
    }
}
