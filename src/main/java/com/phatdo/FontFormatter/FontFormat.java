package com.phatdo.FontFormatter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontFormat {
    public static final String REGULAR_FONT_URL = "src\\main\\resources\\font\\OpenSans-Regular.ttf";
    public static final String BOLD_FONT_URL = "src\\main\\resources\\font\\OpenSans-Bold.ttf";

    public static Font loadFontFromURL(String font_url) {
        try {
            File fontFile = new File(font_url);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(Font.PLAIN, 12); // You can adjust the style and size
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Return a default font if loading fails
            return new Font("Arial", Font.PLAIN, 12);
        }
    }
}
