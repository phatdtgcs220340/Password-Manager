package com.phatdo;

import com.phatdo.Authentication.Authentication;
import com.phatdo.DataProcess.ConnectDatabase;
import com.phatdo.Login.LoginFrame;
import com.phatdo.PasswordManager.PasswordManagerFrame;
import com.phatdo.PasswordManager.SearchFrame;

import javax.swing.*;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }
    public void go() {

        JFrame loginFrame = new LoginFrame();
        while (!Authentication.getAuthenticate()) {
            loginFrame.setVisible(true);
        }
        loginFrame.setVisible(false);
        JFrame pasManFrame = new SearchFrame();
        pasManFrame.setVisible(true);
    }
}