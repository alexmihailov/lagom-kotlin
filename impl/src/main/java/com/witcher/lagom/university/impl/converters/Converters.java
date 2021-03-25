package com.witcher.lagom.university.impl.converters;

import com.witcher.lagom.university.api.data.StudentData;
import com.witcher.lagom.university.impl.persistence.students.StudentEntity;
import javax.annotation.Nullable;
import org.pcollections.PCollection;
import org.pcollections.TreePVector;

import static java.util.stream.Collectors.toList;

public final class Converters {

    private Converters() {
    }

    public static @Nullable StudentData toStudentData(@Nullable StudentEntity entity) {
        if (entity == null) return null;
        return new StudentData(entity.getFirstName(), entity.getMiddleName(),
                entity.getLastName(), entity.getBirthday(), entity.getGroup());
    }

    public static @Nullable PCollection<StudentData> toStudentDataCollection(@Nullable PCollection<StudentEntity> entities) {
        if (entities == null) return null;
        return TreePVector.from(
                entities.stream().map(Converters::toStudentData).collect(toList())
        );
    }
}
