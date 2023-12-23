package com.phatdo;

import com.phatdo.Authentication.Authentication;
import com.phatdo.FrameFormatter.FrameFormat;
import com.phatdo.Login.LoginFrame;
import com.phatdo.PasswordManager.PasswordManagerFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }

    public void go() {

        FrameFormat.setUI();

        JFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        Authentication.authenticationLoop();
        loginFrame.setVisible(false);
        JFrame pasManFrame = new PasswordManagerFrame();
        pasManFrame.setVisible(true);

    }
}