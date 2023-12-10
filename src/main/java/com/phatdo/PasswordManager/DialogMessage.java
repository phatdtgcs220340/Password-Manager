package com.phatdo.PasswordManager;

import javax.swing.*;

public class DialogMessage {
    public static void showErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    public static void showNotificationDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static int showDecisionDialog(String message, String title) {
        int decision = JOptionPane.showConfirmDialog(null, message,
                title, JOptionPane.YES_NO_OPTION);
        return decision;
    }
}
