package com.phatdo.Clipboard;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class AutoCopy {
    public static void main(String[] args) {
        String textToCopy = "Hello, Clipboard!";

        // Create a StringSelection object with the text to be copied
        StringSelection stringSelection = new StringSelection(textToCopy);

        // Get the system clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Set the contents of the clipboard to the StringSelection
        clipboard.setContents(stringSelection, null);

        System.out.println("Text has been copied to the clipboard: " + textToCopy);
    }
    public static void copyToClipboard(String text) {
        // Create a StringSelection object with the text to be copied
        StringSelection stringSelection = new StringSelection(text);

        // Get the system clipboard
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Set the contents of the clipboard to the StringSelection
        clipboard.setContents(stringSelection, null);
    }
}
