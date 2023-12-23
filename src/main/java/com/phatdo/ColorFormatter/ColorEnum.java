package com.phatdo.ColorFormatter;

import java.awt.*;

public enum ColorEnum {
    TEXTFIELD("#0B0B0D", "#FFFFFF"),
    LABEL("#474A56", "#D3D5FD"),
    BACKGROUND("#D3D5FD", "#D3D5FD"),
    TITLEBAR("#0B0B0D", "#FFFFFF"),
    OPTION_PANE("#474A56", "#D3D5FD"),
    COMBO_BOX("#474A56", "#FFFFFF"),
    BUTTON("#756AB6", "#E0AED0");

    private Color fgColor;
    private Color bgColor;

    ColorEnum(String fgColorCode, String bgColorCode) {
        fgColor = Color.decode(fgColorCode);
        bgColor = Color.decode(bgColorCode);
    }

    public Color getFgColor() {
        return fgColor;
    }

    public Color getBgColor() {
        return bgColor;
    }
}
