package com.witcher.lagom.university.impl.utils;

import com.lightbend.lagom.javadsl.api.transport.BadRequest;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;

public final class Preconditions {

    private Preconditions() {
    }

    public static void checkStringNotEmpty(String value, String param) {
        if (StringUtils.isBlank(value)) {
            throw new BadRequest(MessageFormat.format("Parameter {0} must not be empty.", param));
        }
    }
    public static <T> void checkNotNull(T value, String param) {
        if (value == null) {
            throw new BadRequest(MessageFormat.format("Parameter {0} must not be empty.", param));
        }
    }
}
