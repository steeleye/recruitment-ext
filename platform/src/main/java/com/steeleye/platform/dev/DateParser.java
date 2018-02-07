package com.steeleye.platform.dev;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class DateParser {
    private static final Pattern NUMBER = Pattern.compile("\\d*");
    private static final String[] DATE_TIME_FORMATS = {
        "yyyyMMdd'T'HHmm[ss]",
        "yyyyMMdd[ HHmm[ss]]",
        "yyyy-MM-dd[ HH:mm[:ss]]",
        "yyyy/MM/dd[ HH:mm[:ss]]",
        "MM/dd/yyyy[ HH:mm[:ss]]",
        "dd-MM-yyyy[ HH:mm[:ss]]",
        "dd MM yyyy[ HH:mm[:ss]]",
        "dd MMM yyyy[ HH:mm[:ss]]",
        "dd MMMM yyyy[ HH:mm[:ss]]",
    };
    private static final DateTimeFormatter DATE_TIME_FORMATTER = createDateTimeFormatter();

    public static LocalDate parseDate(String text) {
        //TODO IMPLEMENT THIS METHOD
        return LocalDate.of(1970, 1, 1);
    }

    private static DateTimeFormatter createDateTimeFormatter() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.optionalStart().append(ISO_DATE_TIME).optionalEnd();
        for (String pattern : DATE_TIME_FORMATS) {
            builder.optionalStart().appendPattern(pattern).appendFraction(NANO_OF_SECOND, 0, 9, true).optionalEnd();
        }
        return builder.toFormatter();
    }

    private static Instant toInst(String text) { return Instant.ofEpochMilli(Long.parseLong(text)); }
}