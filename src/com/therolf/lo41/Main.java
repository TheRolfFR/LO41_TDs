package com.therolf.lo41;

import com.therolf.lo41.vue.onglets.DefautsDePageOnglet;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("LO41 TDs");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension d = new Dimension(400, 300);
        window.setMinimumSize(d);
        window.setPreferredSize(d);

        JTabbedPane tabbedPane = new JTabbedPane();
        DefautsDePageOnglet.addTabToPane(tabbedPane);

        window.getContentPane().add(tabbedPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // window.getContentPane().add(scrollPane, BorderLayout.SOUTH);

        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
}
