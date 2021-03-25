package com.witcher.lagom.university.api;

import com.lightbend.lagom.javadsl.api.deser.PathParamSerializer;
import com.lightbend.lagom.javadsl.api.transport.BadRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

public final class LocalDatePathParamSerializer implements PathParamSerializer<LocalDate> {

    @Override
    public PSequence<String> serialize(LocalDate parameter) {
        return TreePVector.singleton(parameter.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
    @Override
    public LocalDate deserialize(PSequence<String> parameters) {
        try {
            return LocalDate.parse(parameters.get(0), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException | NullPointerException | IndexOutOfBoundsException e) {
            throw new BadRequest("Invalid parameter value or missing required parameter");
        }
    }
}
