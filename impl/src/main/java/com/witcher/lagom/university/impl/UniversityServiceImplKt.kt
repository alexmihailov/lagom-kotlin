package com.witcher.lagom.university.impl

import akka.NotUsed
import com.google.inject.Inject
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.deser.ExceptionMessage
import com.lightbend.lagom.javadsl.api.transport.TransportErrorCode
import com.lightbend.lagom.javadsl.api.transport.TransportException
import com.witcher.lagom.university.api.UniversityServiceKt
import com.witcher.lagom.university.api.data.StudentDataKt
import com.witcher.lagom.university.impl.converters.toStudentDataKt
import com.witcher.lagom.university.impl.services.students.StudentsServiceKt
import com.witcher.lagom.university.impl.utils.checkNotNullOrThrow
import com.witcher.lagom.university.impl.utils.checkStringNotEmpty
import mu.KotlinLogging
import org.taymyr.lagom.javadsl.api.serviceCall
import java.time.LocalDate
import java.util.*


private val log = KotlinLogging.logger {}

class UniversityServiceImplKt @Inject constructor(
    private val studentsService: StudentsServiceKt
): UniversityServiceKt {

    override fun getStudents(
        firstName: Optional<String>,
        middleName: Optional<String>,
        lastName: Optional<String>,
        birthday: Optional<LocalDate>,
        group: Optional<String>
    ): ServiceCall<NotUsed, List<StudentDataKt>> = serviceCall {
        try {
            val students = studentsService.getStudents(
                firstName = firstName.orElse(null),
                middleName = middleName.orElse(null),
                lastName = lastName.orElse(null),
                birthday = birthday.orElse(null),
                group = group.orElse(null)
            )
            students.map { it.toStudentDataKt() }
        } catch (th: Throwable) {
            throw th.checkExceptionally()
        }
    }

    override fun createStudent(): ServiceCall<StudentDataKt, StudentDataKt> = serviceCall { request ->
        try {
            checkStringNotEmpty(request.firstName, "firstName")
            checkStringNotEmpty(request.middleName, "middleName")
            checkStringNotEmpty(request.lastName, "lastName")
            checkNotNullOrThrow(request.birthday, "birthday")
            checkStringNotEmpty(request.group, "group")
            val entity = studentsService.createStudent(
                firstName = request.firstName, middleName = request.middleName,
                lastName = request.lastName, birthday = request.birthday,
                group = request.group
            )
            entity.toStudentDataKt()
        } catch (th: Throwable) {
            throw th.checkExceptionally()
        }
    }

    private fun Throwable.checkExceptionally(): TransportException {
        log.error(this) { "Error while executing request." }
        return TransportException(
            TransportErrorCode.InternalServerError,
            ExceptionMessage("InternalError", this.message)
        )
    }
}
