package com.witcher.lagom.university.impl.services.students

import com.google.inject.Inject
import com.witcher.lagom.university.impl.persistence.students.StudentEntityKt
import com.witcher.lagom.university.impl.persistence.students.StudentsRepositoryKt
import kotlinx.coroutines.future.await
import java.time.LocalDate

class StudentsServiceKt @Inject constructor(
    private val studentsRepository: StudentsRepositoryKt
) {
    suspend fun getStudents(
        firstName: String?,
        middleName: String?,
        lastName: String?,
        birthday: LocalDate?,
        group: String?
    ) : List<StudentEntityKt> = studentsRepository.getStudents(
        firstName = firstName, lastName = lastName,
        middleName = middleName, birthday = birthday, group = group
    )

    suspend fun createStudent(
        firstName: String,
        middleName: String,
        lastName: String,
        birthday: LocalDate,
        group: String
    ) : StudentEntityKt {
        val student = StudentEntityKt(firstName = firstName, middleName = middleName,
            lastName = lastName, birthday = birthday, group = group
        )
        studentsRepository.create(student).await()
        return student
    }
}
