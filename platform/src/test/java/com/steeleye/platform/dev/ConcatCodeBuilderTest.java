package com.steeleye.platform.dev;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.google.common.collect.ImmutableList.of;
import static com.steeleye.platform.dev.MapBuilder.mapOf;
import static gcardone.junidecode.Junidecode.unidecode;
import static java.lang.Character.toUpperCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ConcatCodeBuilderTest extends ConcatCodeBuilder {
    private static Map<Character, Character> UNICODE_CHARS = ImmutableMap.<Character, Character>builder()
        .put('\u00C4', 'A').put('\u00E4', 'A').put('\u00C0', 'A').put('\u00E0', 'A').put('\u00C1', 'A').put('\u00E1', 'A').put('\u00C2', 'A')
        .put('\u00E2', 'A').put('\u00C3', 'A').put('\u00E3', 'A').put('\u00C5', 'A').put('\u00E5', 'A').put('\u01CD', 'A').put('\u01CE', 'A')
        .put('\u0104', 'A').put('\u0105', 'A').put('\u0102', 'A').put('\u0103', 'A').put('\u00C6', 'A').put('\u00E6', 'A').put('\u00C7', 'C')
        .put('\u00E7', 'C').put('\u0106', 'C').put('\u0107', 'C').put('\u0108', 'C').put('\u0109', 'C').put('\u010C', 'C').put('\u010D', 'C')
        .put('\u010E', 'D').put('\u0111', 'D').put('\u0110', 'D').put('\u010F', 'D').put('\u00F0', 'D').put('\u00C8', 'E').put('\u00E8', 'E')
        .put('\u00C9', 'E').put('\u00E9', 'E').put('\u00CA', 'E').put('\u00EA', 'E').put('\u00CB', 'E').put('\u00EB', 'E').put('\u011A', 'E')
        .put('\u011B', 'E').put('\u0118', 'E').put('\u0119', 'E').put('\u011C', 'G').put('\u011D', 'G').put('\u0122', 'G').put('\u0123', 'G')
        .put('\u011E', 'G').put('\u011F', 'G').put('\u0124', 'H').put('\u0125', 'H').put('\u00CC', 'I').put('\u00EC', 'I').put('\u00CD', 'I')
        .put('\u00ED', 'I').put('\u00CE', 'I').put('\u00EE', 'I').put('\u00CF', 'I').put('\u00EF', 'I').put('\u0131', 'I').put('\u0134', 'J')
        .put('\u0135', 'J').put('\u0136', 'K').put('\u0137', 'K').put('\u0139', 'L').put('\u013A', 'L').put('\u013B', 'L').put('\u013C', 'L')
        .put('\u0141', 'L').put('\u0142', 'L').put('\u013D', 'L').put('\u013E', 'L').put('\u00D1', 'N').put('\u00F1', 'N').put('\u0143', 'N')
        .put('\u0144', 'N').put('\u0147', 'N').put('\u0148', 'N').put('\u00D6', 'O').put('\u00F6', 'O').put('\u00D2', 'O').put('\u00F2', 'O')
        .put('\u00D3', 'O').put('\u00F3', 'O').put('\u00D4', 'O').put('\u00F4', 'O').put('\u00D5', 'O').put('\u00F5', 'O').put('\u0150', 'O')
        .put('\u0151', 'O').put('\u00D8', 'O').put('\u00F8', 'O').put('\u0152', 'O').put('\u0153', 'O').put('\u0154', 'R').put('\u0155', 'R')
        .put('\u0158', 'R').put('\u0159', 'R').put('\u1E9E', 'S').put('\u00DF', 'S').put('\u015A', 'S').put('\u015B', 'S').put('\u015C', 'S')
        .put('\u015D', 'S').put('\u015E', 'S').put('\u015F', 'S').put('\u0160', 'S').put('\u0161', 'S').put('\u0218', 'S').put('\u0219', 'S')
        .put('\u0164', 'T').put('\u0165', 'T').put('\u0162', 'T').put('\u0163', 'T').put('\u00DE', 'T').put('\u00FE', 'T').put('\u021A', 'T')
        .put('\u021B', 'T').put('\u00DC', 'U').put('\u00FC', 'U').put('\u00D9', 'U').put('\u00F9', 'U').put('\u00DA', 'U').put('\u00FA', 'U')
        .put('\u00DB', 'U').put('\u00FB', 'U').put('\u0170', 'U').put('\u0171', 'U').put('\u0168', 'U').put('\u0169', 'U').put('\u0172', 'U')
        .put('\u0173', 'U').put('\u016E', 'U').put('\u016F', 'U').put('\u0174', 'W').put('\u0175', 'W').put('\u00DD', 'Y').put('\u00FD', 'Y')
        .put('\u0178', 'Y').put('\u00FF', 'Y').put('\u0176', 'Y').put('\u0177', 'Y').put('\u0179', 'Z').put('\u017A', 'Z').put('\u017D', 'Z')
        .put('\u017E', 'Z').put('\u017B', 'Z').put('\u017C', 'Z')
        .build();

    public static Object[][] concatInputs() {
        return new Object[][]{
            {of(), "1970/01/01", "foo", "bar", null},
            {of("IE"), null, "foo", "bar", null},
            {of("IE"), "1970/01/01", null, "bar", null},
            {of("IE"), "1970/01/01", "foo", null, null},
            {of("IE"), "1980-01-13 10:15:30", "John", "O'Brian", "IE19800113JOHN#OBRIA"},
            {of("HU"), "1981-02-14 10:15:30", "Ludwig", "Van der Rohe", "HU19810214LUDWIROHE#"},
            {of("US"), "101658634000", "Victor", "Vandenberg", "US19730322VICTOVANDE"},
            {of("NO"), "101658634000", "Eli", "Ødegård", "NO19730322ELI##ODEGA"},
            {of("LU"), "101658634000", "Willeke", "de Bruijn", "LU19730322WILLEBRUIJ"},
            {of("US"), "101658634000", "Dr. Jon Ian", "Dewitt", "US19730322JON##DEWIT"},
            {of("PT"), "101658634000", "Amy-Ally", "Garção de Magalhães", "PT19730322AMYALGARCA"},
            {of("FR"), "18 Jun 1990", "Giovani", "dos Santos", "FR19900618GIOVASANTO"},
            {of("DE"), "18 Jun 1990", "Günter", "Voẞ", "DE19900618GUNTEVOS##"},
            {of("FI"), "18 Jun 1990", "Åke", "Öjvind", "FI19900618AKE##OJVIN"},
            {of("NL"), "18 Jun 1990", "Jan", "Van De Merwe", "NL19900618JAN##MERWE"},
            {of("IS"), "18 Jun 1990", "Äärnästi", "Friðjóna", "IS19900618AARNAFRIDJ"},
            {of("SE"), "23 December 1975", "Hlégestur", "Norðmaðr", "SE19751223HLEGENORDM"},
            {of("GB", "CA", "FR"), "23 December 1975", "Mary-Jane", "Montana", "CA19751223MARYJMONTA"},
            {of("CZ"), "1957-01-03T03:15:30.987Z", "ALŽBĚTA", "BLAŽEJ", "CZ19570103ALZBEBLAZE"},
            {of("PL", "HU"), "23 December 1975", "ANDŻELIKA", "BOGUSŁAWA", "HU19751223ANDZEBOGUS"},
            {of("ES", "FR"), "1969/10/31", "ŻAKLINA", "SZCZĘSNY", "ES19691031ZAKLISZCZE"},
            {of("GB"), "1972-05-06", "Альберт", "Беломестных", "GB19720506ALBIEBIELO"},
            {of("GR"), "1983/11/11", "Ευριπιδης", "Θεμιστοκλης ", "GR19831111EURIPTHEMI"},
        };
    }

    @Test @Parameters(method = "concatInputs")
    public void buildConcatCodeSuccess(List<String> nationality, String dob, String first, String last, String expected) throws Exception {
        Map<String, Object> user = mapOf("personalDetails", mapOf("nationality", nationality,
            "firstName", first, "lastName", last, "dob", dob));
        assertThat(buildConcatCode(user), is(expected));
    }

    @Test
    public void assertJunicodeCompatibilityToEsmaSpec() {
        Set<Character> faileds = new TreeSet<>();
        for (Character ch : UNICODE_CHARS.keySet()) {
            char oldcode = UNICODE_CHARS.get(ch);
            char newcode = toUpperCase(unidecode(ch.toString()).charAt(0));
            if (newcode != oldcode) { faileds.add(ch); }
        }
        assertThat(faileds, is(ImmutableSet.of('\u00DD', '\u1E9E')));
    }
}