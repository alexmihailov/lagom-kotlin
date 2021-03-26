package com.witcher.lagom.university.api

import akka.NotUsed
import com.lightbend.lagom.javadsl.api.Descriptor
import com.lightbend.lagom.javadsl.api.Service
import com.lightbend.lagom.javadsl.api.Service.named
import com.lightbend.lagom.javadsl.api.Service.restCall
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.transport.Method
import com.witcher.lagom.university.api.data.StudentDataKt
import java.time.LocalDate
import java.util.*
import kotlin.reflect.jvm.javaMethod

const val SERVICE_NAME = "university"

interface UniversityServiceKt : Service {

    fun getStudents(
        firstName: Optional<String>,
        middleName: Optional<String>,
        lastName: Optional<String>,
        birthday: Optional<LocalDate>,
        group: Optional<String>
    ): ServiceCall<NotUsed, List<StudentDataKt>>

    fun createStudent(): ServiceCall<StudentDataKt, StudentDataKt>

    @JvmDefault
    override fun descriptor(): Descriptor = named(SERVICE_NAME).withCalls(
        restCall<NotUsed, List<StudentDataKt>>(
            Method.GET,
            "/api/$SERVICE_NAME/students?firstName&middleName&lastName&birthday&group",
            UniversityServiceKt::getStudents.javaMethod
        ),
        restCall<StudentDataKt, StudentDataKt>(
            Method.POST,
            "/api/$SERVICE_NAME/students",
            UniversityServiceKt::createStudent.javaMethod
        )
    ).withAutoAcl(true)
        .withPathParamSerializer(LocalDate::class.java, LocalDatePathParamSerializerKt)
}