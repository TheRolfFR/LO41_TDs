package com.therolf.lo41.vue.onglets;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public abstract class Onglet {

    protected static final int BORDER_THICKNESS = 5;

    public static void addTabToPane(JTabbedPane tabbedPane) {}

    protected static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
