package com.witcher.lagom.university.api

import com.lightbend.lagom.javadsl.api.deser.PathParamSerializer
import com.lightbend.lagom.javadsl.api.transport.BadRequest
import org.pcollections.PSequence
import org.pcollections.TreePVector
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDatePathParamSerializerKt : PathParamSerializer<LocalDate> {
    override fun serialize(parameter: LocalDate): TreePVector<String> =
        TreePVector.singleton(parameter.format(DateTimeFormatter.ISO_LOCAL_DATE))

    override fun deserialize(parameters: PSequence<String>): LocalDate = try {
        LocalDate.parse(parameters[0], DateTimeFormatter.ISO_LOCAL_DATE)
    } catch (e: Exception) {
        throw BadRequest("Invalid parameter value or missing required parameter")
    }
}
