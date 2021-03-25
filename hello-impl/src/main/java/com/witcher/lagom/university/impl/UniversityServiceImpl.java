package com.witcher.lagom.university.impl;

import akka.NotUsed;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.deser.ExceptionMessage;
import com.lightbend.lagom.javadsl.api.transport.BadRequest;
import com.lightbend.lagom.javadsl.api.transport.TransportErrorCode;
import com.lightbend.lagom.javadsl.api.transport.TransportException;
import com.witcher.lagom.university.api.data.StudentData;
import com.witcher.lagom.university.impl.converters.Converters;
import com.witcher.lagom.university.impl.services.students.StudentsService;
import java.text.MessageFormat;
import java.time.LocalDate;
import com.witcher.lagom.university.api.UniversityService;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.pcollections.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the HelloService.
 */
public class UniversityServiceImpl implements UniversityService {

  private final static Logger LOG = LoggerFactory.getLogger(UniversityServiceImpl.class);

  private final StudentsService studentsService;

  @Inject
  public UniversityServiceImpl(StudentsService studentsService) {
    this.studentsService = studentsService;
  }

  @Override
  public ServiceCall<NotUsed, PCollection<StudentData>> getStudents(
          Optional<String> firstName,
          Optional<String> middleName,
          Optional<String> lastName,
          Optional<LocalDate> birthday,
          Optional<String> group) {
    return notUsed -> studentsService.getStudents(
            firstName.orElse(null),
            middleName.orElse(null),
            lastName.orElse(null),
            birthday.orElse(null),
            group.orElse(null)
    ).thenApply(Converters::toStudentDataCollection)
            .exceptionally(UniversityServiceImpl::checkExceptionally);
  }

  @Override
  public ServiceCall<StudentData, StudentData> createStudent() {
    return request -> {
      checkStringNotEmpty(request.getFirstName(), "firstName");
      checkStringNotEmpty(request.getMiddleName(), "middleName");
      checkStringNotEmpty(request.getLastName(), "lastName");
      checkNotNull(request.getBirthday(), "birthday");
      checkStringNotEmpty(request.getGroup(), "group");
      return studentsService.createStudent(
              request.getFirstName(),
              request.getMiddleName(),
              request.getLastName(),
              request.getBirthday(),
              request.getGroup()
      ).thenApply(Converters::toStudentData)
              .exceptionally(UniversityServiceImpl::checkExceptionally);
    };
  }

  private static <T> T checkExceptionally(Throwable ex) {
    LOG.error("Error while executing request.", ex);
    throw new TransportException(TransportErrorCode.InternalServerError,
            new ExceptionMessage("InternalError", ex.getMessage()));
  }

  private static void checkStringNotEmpty(String value, String param) {
    if (StringUtils.isBlank(value)) {
      throw new BadRequest(MessageFormat.format("Parameter {0} must not be empty.", param));
    }
  }
  private static <T> void checkNotNull(T value, String param) {
    if (value == null) {
      throw new BadRequest(MessageFormat.format("Parameter {0} must not be empty.", param));
    }
  }
}
