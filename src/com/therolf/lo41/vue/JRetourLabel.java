package com.therolf.lo41.vue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class JRetourLabel extends JLabel {

    public static final int BORDER_THICKNESS = 3;
    public static final Border LABEL_BORDER = new EmptyBorder(BORDER_THICKNESS*3, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS*2);
    public static final Border PANEL_BORDER = new EmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS);

    public JRetourLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);

        String t = text.replace("\n", "<br>");

        setText("<html>" + t + "</html>");
    }

    public JRetourLabel(String text) {
        super(text);

        String t = text.replace("\n", "<br>");

        setText("<html>" + t + "</html>");
    }
}
