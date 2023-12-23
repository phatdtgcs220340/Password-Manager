package com.phatdo.FrameFormatter;

import javax.swing.UIManager;

import com.phatdo.ColorFormatter.ColorEnum;
import com.phatdo.FontFormatter.FontFormat;

public class FrameFormat {
    public static void setUI() {
        UIManager.put("Label.foreground", ColorEnum.LABEL.getFgColor());
        UIManager.put("TextField.foreground", ColorEnum.TEXTFIELD.getFgColor());
        UIManager.put("TextField.background", ColorEnum.TEXTFIELD.getBgColor());
        UIManager.put("PasswordField.foreground", ColorEnum.TEXTFIELD.getFgColor());
        UIManager.put("PasswordField.background", ColorEnum.TEXTFIELD.getBgColor());
        UIManager.put("ComboBox.foreground", ColorEnum.COMBO_BOX.getFgColor());
        UIManager.put("ComboBox.background", ColorEnum.COMBO_BOX.getBgColor());
        UIManager.put("TextArea.foreground", ColorEnum.TEXTFIELD.getFgColor());
        UIManager.put("TextArea.background", ColorEnum.TEXTFIELD.getBgColor());
        UIManager.put("Button.foreground", ColorEnum.BUTTON.getFgColor());
        UIManager.put("Button.background", ColorEnum.BUTTON.getBgColor());
        UIManager.put("OptionPane.messageForeground", ColorEnum.OPTION_PANE.getFgColor());
        UIManager.put("OptionPane.background", ColorEnum.OPTION_PANE.getBgColor());
        UIManager.put("Panel.background", ColorEnum.BACKGROUND.getBgColor());
        UIManager.put("OptionPane.messageFont", FontFormat.loadFontFromURL(FontFormat.REGULAR_FONT_URL));
        UIManager.put("TextField.font", FontFormat.loadFontFromURL(FontFormat.REGULAR_FONT_URL));
        UIManager.put("PasswordField.font", FontFormat.loadFontFromURL(FontFormat.REGULAR_FONT_URL));
        UIManager.put("ComboBox.font", FontFormat.loadFontFromURL(FontFormat.REGULAR_FONT_URL));
        UIManager.put("Button.font", FontFormat.loadFontFromURL(FontFormat.BOLD_FONT_URL));
        UIManager.put("TextArea.font", FontFormat.loadFontFromURL(FontFormat.REGULAR_FONT_URL));
        UIManager.put("Label.font", FontFormat.loadFontFromURL(FontFormat.BOLD_FONT_URL));
    }
}
