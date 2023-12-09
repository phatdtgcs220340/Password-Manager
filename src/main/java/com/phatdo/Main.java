package com.phatdo;

import com.phatdo.Authentication.Authentication;
import com.phatdo.Login.LoginFrame;
import com.phatdo.PasswordManager.PasswordManagerFrame;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }
    public void go() {

        JFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        boolean isAuthenticated;
        while(true) {
            isAuthenticated = Authentication.getAuthenticate();
            try {
                // Sleep for 0.2 seconds (200 milliseconds)
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isAuthenticated)
                break;
        }
        loginFrame.setVisible(false);
        JFrame pasManFrame = new PasswordManagerFrame();
        pasManFrame.setVisible(true);
    }
}