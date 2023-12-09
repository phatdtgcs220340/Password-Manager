package com.phatdo.StringFormatter;

public class StringFormat {
    public static void main(String[] args) {
        System.out.println(toCapitalize("concac a"));
    }
    public static String toCapitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

}
