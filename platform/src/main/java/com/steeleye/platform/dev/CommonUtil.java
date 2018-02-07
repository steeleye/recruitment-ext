package com.steeleye.platform.dev;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class CommonUtil {
    public static String safePrefix(String word, String delimiter) { return safePrefix(word, delimiter, ""); }

    public static String safePrefix(String word, String delimiter, String defValue) {
        return word.contains(delimiter) ? word.substring(0, word.indexOf(delimiter)) : defValue;
    }

    public static String safeSuffix(String word, String delimiter) { return word.substring(word.indexOf(delimiter) + delimiter.length()); }
}