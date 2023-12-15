package com.phatdo.PasswordManager;

import javax.swing.*;

public class DialogMessage {

    public static void showErrorDialog(String message, String title) {
        Icon customIcon = new ImageIcon(new ImageIcon("src/main/resources/img/dialog_message_icon/cross.png")
                .getImage());
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, customIcon);
    }
    public static void showNotificationDialog(String message, String title) {
        // Load and scale the custom icon
        Icon customIcon = new ImageIcon(new ImageIcon("src/main/resources/img/dialog_message_icon/check.png")
                .getImage());

        // Show the dialog
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                customIcon
        );
    }
    public static int showDecisionDialog(String message, String title) {
        // Load and scale the custom icon
        Icon customIcon = new ImageIcon(new ImageIcon("src/main/resources/img/dialog_message_icon/decision.png")
                .getImage());

        // Show the dialog
        int decision = JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, // Use QUESTION_MESSAGE for a question icon
                customIcon,
                new Object[]{"Yes", "No"}, // Options
                "Yes" // Default selection
        );

        // Handle the case where the user closes the dialog
        if (decision == JOptionPane.CLOSED_OPTION) {
            // Default to a specific value or handle as needed
            decision = -1; // For example, set to -1 to indicate the dialog was closed
        }

        return decision;
    }
}
