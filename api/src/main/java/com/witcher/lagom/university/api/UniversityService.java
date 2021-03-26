package com.witcher.lagom.university.api;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import static com.lightbend.lagom.javadsl.api.Service.restCall;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.deser.PathParamSerializer;
import com.lightbend.lagom.javadsl.api.transport.BadRequest;
import com.lightbend.lagom.javadsl.api.transport.Method;
import com.witcher.lagom.university.api.data.StudentData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.pcollections.PCollection;
import org.pcollections.PSequence;
import org.pcollections.TreePVector;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Hello.
 */
public interface UniversityService extends Service {

  String SERVICE_NAME = "university";

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  ServiceCall<NotUsed, PCollection<StudentData>> getStudents(
          Optional<String> firstName,
          Optional<String> middleName,
          Optional<String> lastName,
          Optional<LocalDate> birthday,
          Optional<String> group
  );

  ServiceCall<StudentData, StudentData> createStudent();

  @Override
  default Descriptor descriptor() {
    return named(SERVICE_NAME).withCalls(
            restCall(Method.GET,
                    "/api/" + SERVICE_NAME + "/students?firstName&middleName&lastName&birthday&group",
                    this::getStudents
            ),
            restCall(Method.POST,
                    "/api/" + SERVICE_NAME + "/students",
                    this::createStudent
            ))
            .withAutoAcl(true)
            .withPathParamSerializer(LocalDate.class, LocalDatePathParamSerializer.getInstance());
  }
}
