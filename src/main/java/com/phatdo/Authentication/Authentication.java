package com.phatdo.Authentication;

public class Authentication {
    private static boolean authenticate = false;

    public static void setAuthenticate(boolean decision) {
        authenticate = decision;
    }

    public static boolean getAuthenticate() {
        return authenticate;
    }

    public static void authenticationLoop() {
        boolean state;
        do {
            state = Authentication.getAuthenticate();
            try {
                // Sleep for 0.2 seconds (200 milliseconds)
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!state);
    }

}
