package org.tobkir.rest.logic;

import jakarta.enterprise.context.RequestScoped;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RequestScoped
public class DateParserRestHelper {

    static final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    public ZonedDateTime parseQueryParamStringToZDT(String dateString) {
        return ZonedDateTime.parse(dateString, formatter.withZone(ZoneId.systemDefault()));
    }
}
