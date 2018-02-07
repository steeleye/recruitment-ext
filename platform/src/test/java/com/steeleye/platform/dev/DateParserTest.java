package com.steeleye.platform.dev;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class DateParserTest extends DateParser {

    public static Object[][] dateTimeInputs() {
        return new Object[][]{
            {"2011-12-03T10:15:30+01:00:00", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30-08:30", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987654321Z", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987654Z", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987654", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987Z", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30.987", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30Z", LocalDate.of(2011, 12, 3)},
            {"2011-12-03T10:15:30", LocalDate.of(2011, 12, 3)},
            {"20111203T101530.123", LocalDate.of(2011, 12, 3)},
            {"20111203 101530.123", LocalDate.of(2011, 12, 3)},
            {"20111203T101530", LocalDate.of(2011, 12, 3)},
            {"20111203 101530", LocalDate.of(2011, 12, 3)},
            {"2016-11-04 06:00:00.987654321", LocalDate.of(2016, 11, 4)},
            {"2011/12/03 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"12/03/2011 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"03-12-2011 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"03 12 2011 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"03 December 2011 10:15:30.987654321", LocalDate.of(2011, 12, 3)},
            {"2016-11-04 06:00:00.345678", LocalDate.of(2016, 11, 4)},
            {"2011/12/03 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"12/03/2011 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"03-12-2011 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"03 12 2011 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"03 December 2011 10:15:30.345678", LocalDate.of(2011, 12, 3)},
            {"2016-11-04 06:00:00.123", LocalDate.of(2016, 11, 4)},
            {"2011/12/03 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"12/03/2011 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"03-12-2011 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"03 12 2011 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"03 December 2011 10:15:30.123", LocalDate.of(2011, 12, 3)},
            {"2011-12-03 10:15:30", LocalDate.of(2011, 12, 3)},
            {"2011/12/03 10:15:30", LocalDate.of(2011, 12, 3)},
            {"12/03/2011 10:15:30", LocalDate.of(2011, 12, 3)},
            {"03-12-2011 10:15:30", LocalDate.of(2011, 12, 3)},
            {"03 12 2011 10:15:30", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011 10:15:30", LocalDate.of(2011, 12, 3)},
            {"03 December 2011 10:15:30", LocalDate.of(2011, 12, 3)},
            {"20111203T1015", LocalDate.of(2011, 12, 3)},
            {"20111203 1015", LocalDate.of(2011, 12, 3)},
            {"2011-12-03 10:15", LocalDate.of(2011, 12, 3)},
            {"2011/12/03 10:15", LocalDate.of(2011, 12, 3)},
            {"12/03/2011 10:15", LocalDate.of(2011, 12, 3)},
            {"03-12-2011 10:15", LocalDate.of(2011, 12, 3)},
            {"03 12 2011 10:15", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011 10:15", LocalDate.of(2011, 12, 3)},
            {"03 December 2011 10:15", LocalDate.of(2011, 12, 3)},
            {"2011-12-03", LocalDate.of(2011, 12, 3)},
            {"2011/12/03", LocalDate.of(2011, 12, 3)},
            {"12/03/2011", LocalDate.of(2011, 12, 3)},
            {"03-12-2011", LocalDate.of(2011, 12, 3)},
            {"03 12 2011", LocalDate.of(2011, 12, 3)},
            {"03 Dec 2011", LocalDate.of(2011, 12, 3)},
            {"03 December 2011", LocalDate.of(2011, 12, 3)},
            {"1479832319000", LocalDate.of(2016, 11, 22)},
        };
    }

    @Test @Parameters(method = "dateTimeInputs")
    public void testMultiFormatDateTime(String dateTime, LocalDate expected) {
        assertThat(expected.toString(), parseDate(dateTime), is(expected));
    }
}