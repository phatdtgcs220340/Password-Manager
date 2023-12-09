package com.phatdo.StringFormatter;

import java.util.Objects;

public class StringFormat {
    public static void main(String[] args) {
        System.out.println(toCapitalize(""));
    }
    public static String toCapitalize(String text) {
        if (Objects.equals(text, "")) {
            return "";
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}
