package com.phatdo.Authentication;

public class Authentication {
    private static boolean authenticate = false;

    public static void setAuthenticate(boolean decision) {
        authenticate = decision;
    }
    public static boolean getAuthenticate() {
        return authenticate;
    }

}
