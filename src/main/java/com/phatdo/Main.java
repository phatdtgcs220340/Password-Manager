package com.phatdo;

import com.phatdo.Authentication.Authentication;
import com.phatdo.Login.LoginFrame;
import com.phatdo.PasswordManager.PasswordManagerFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }

    public void go() {
        UIManager.put("Label.foreground", Color.decode("#7C93C3"));
        UIManager.put("TextField.foreground", Color.decode("#164863"));
        UIManager.put("ComboBox.foreground", Color.decode("#164863"));
        UIManager.put("ComboBox.background", Color.decode("#9EB8D9"));
        UIManager.put("TextArea.foreground", Color.decode("#164863"));
        UIManager.put("Button.foreground", Color.decode("#164863"));
        UIManager.put("Button.background", Color.decode("#9EB8D9"));
        JFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        boolean isAuthenticated;
        do {
            isAuthenticated = Authentication.getAuthenticate();
            try {
                // Sleep for 0.2 seconds (200 milliseconds)
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!isAuthenticated);
        loginFrame.setVisible(false);
        JFrame pasManFrame = new PasswordManagerFrame();
        pasManFrame.setVisible(true);

    }
}