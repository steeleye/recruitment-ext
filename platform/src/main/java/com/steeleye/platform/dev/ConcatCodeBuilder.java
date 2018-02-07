package com.steeleye.platform.dev;

import java.time.LocalDate;
import java.util.Map;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.getFirst;
import static com.steeleye.platform.dev.DateParser.parseDate;
import static com.steeleye.platform.dev.DeepRetriever.deepCollect;
import static com.steeleye.platform.dev.DeepRetriever.deepRetrieve;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class ConcatCodeBuilder {
    private static final String[] INVALID_PREFIX = new String[]{"MHIC GIOLLA ", "VAN DEN ", "VAN DER ", "VON DEM ", "VON DEN ", "VON DER ", "VAN DE ",
        "DE LA ", "DE LE ", "DE L ", "AM ", "AUF ", "AUS ", "DEM ", "DER ", "D ", "DA ", "DE ", "DEL ", "DI ", "DO ", "DOS ", "DU ",
        "IM ", "LA ", "LE ", "MAC ", "MC ", "MHAC ", "MHIC ", "MIC ", "NI ", "NIC ", "O ", "UA ", "UI ", "VAN ", "VOM ", "VON "};

    private static final String[] TITLES = new String[]{"ATTY ", "COACH ", "DAME ", "DR ", "FR ", "GOV ", "HONORABLE ",
        "MADAME ", "MADAM ", "MAID ", "MASTER ", "MISS ", "MONSIEUR ", "MR ", "MRS ", "MS ", "MX ", "OFC ", "PHD ", "PRES ", "PROF ", "REV ", "SIR "};

    public static String buildConcatId(Map<String, Object> user) {
        String nationality = getFirst(deepCollect(user, "personalDetails.nationality", String.class), "");
        String dateOfBirth = deepRetrieve(user, "personalDetails.dob");
        String firstName = deepRetrieve(user, "personalDetails.firstName");
        String lastName = deepRetrieve(user, "personalDetails.lastName");

        return isNullOrEmpty(firstName) || isNullOrEmpty(lastName) || isNullOrEmpty(dateOfBirth) || isNullOrEmpty(nationality) ? null :
            buildConcatId(nationality, parseDate(dateOfBirth), firstName, lastName);
    }

    private static String buildConcatId(String nationality, LocalDate dateOfBirth, String firstName, String lastName) {
        //TODO IMPLEMENT THIS METHOD
        return null;
    }
}