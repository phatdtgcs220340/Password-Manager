package com.phatdo.StringFormatter;

import java.util.Objects;

public class StringFormat {
    public static String toCapitalize(String text) {
        text = removeLeadingTrailingSpaces(text);
        if (Objects.equals(text, "")) {
            return "";
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static String removeLeadingTrailingSpaces(String input) {
        // Use the trim() method to remove leading and trailing spaces
        return input.trim();
    }

    public static String encodeText(char character, int times) {
        StringBuilder result = new StringBuilder();

        result.append(String.valueOf(character).repeat(Math.max(0, times)));

        return result.toString();
    }
}
