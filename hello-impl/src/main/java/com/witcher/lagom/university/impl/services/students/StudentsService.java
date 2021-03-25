package com.witcher.lagom.university.impl.services.students;

import com.google.inject.Inject;
import com.witcher.lagom.university.impl.persistence.students.StudentEntity;
import com.witcher.lagom.university.impl.persistence.students.StudentsRepository;
import java.time.LocalDate;
import java.util.concurrent.CompletionStage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.pcollections.PCollection;
import org.pcollections.TreePVector;

public class StudentsService {

    private final StudentsRepository studentsRepository;

    @Inject
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public CompletionStage<PCollection<StudentEntity>> getStudents(
            @Nullable String firstName,
            @Nullable String middleName,
            @Nullable String lastName,
            @Nullable LocalDate birthday,
            @Nullable String group
    ) {
        return studentsRepository.getStudents(firstName, middleName, lastName, birthday, group)
                .thenApply(TreePVector::from);
    }

    public CompletionStage<StudentEntity> createStudent(
            @Nonnull String firstName,
            @Nonnull String middleName,
            @Nonnull String lastName,
            @Nonnull LocalDate birthday,
            @Nonnull String group
    ) {
        StudentEntity student = new StudentEntity(firstName, middleName, lastName, birthday, group);
        return studentsRepository.create(student).thenApply(done -> student);
    }
}
