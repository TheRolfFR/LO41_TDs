package com.therolf.lo41.vue.onglets;

import com.therolf.lo41.controleur.listeners.MouseClickListener;
import com.therolf.lo41.modele.defautsDePage.AlgoDefautDePage;
import com.therolf.lo41.modele.defautsDePage.AlgoLfu;
import com.therolf.lo41.modele.defautsDePage.AlgoLru;
import com.therolf.lo41.modele.defautsDePage.AlgoPaps;
import com.therolf.lo41.vue.defautsDePage.FenetreResultat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("unused")
public class DefautsDePageOnglet extends Onglet {

    private static JTextField nbPagesField;
    private static JTextField sequence;
    private static JComboBox<String> algorithme;

    public static void addTabToPane(JTabbedPane tabbedPane) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS, BORDER_THICKNESS));

        JPanel top = new JPanel(new BorderLayout(10, 10));
        JLabel labelPages = new JLabel("Donnez le nombre de pages: ");
        nbPagesField = new JTextField("3");
        nbPagesField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent eve) {
                String AllowedData="0123456789";
                char enter = eve.getKeyChar();
                if (!AllowedData.contains(String.valueOf(enter))) {
                    eve.consume();
                }
            }
        });
        JLabel instructions = new JLabel("Donnez la séquence d'entrée à ordonner:");
        top.add(labelPages, BorderLayout.NORTH);
        top.add(nbPagesField, BorderLayout.CENTER);
        top.add(instructions, BorderLayout.SOUTH);

        sequence = new JTextField();
        sequence.setText("3 1 2 3 4 5 3 2 1 5 1 2 3");
        sequence.setToolTipText("Séquence");
        panel.add(top, BorderLayout.NORTH);
        panel.add(sequence, BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout(10 ,10));
        JLabel methode = new JLabel("Méthode utilisée");
        algorithme = new JComboBox<>(new String[]{"PAPS", "LRU", "LFU"});
        JButton demarrer = new JButton("Démarrer");
        demarrer.addMouseListener((MouseClickListener) e -> lancerReso());
        south.add(methode, BorderLayout.NORTH);
        south.add(algorithme, BorderLayout.CENTER);
        south.add(demarrer, BorderLayout.SOUTH);

        panel.add(south, BorderLayout.SOUTH);

        tabbedPane.addTab("Défauts de page", null, panel,
                null);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
    }

    private static void lancerReso() {
        String[] tab = sequence.getText().trim().split("\\s+");

        Integer[] val = new Integer[tab.length];

        for (int i = 0; i < tab.length; i++) {
            String t = tab[i];

            try {
                int v = Integer.parseInt(t);
                val[i] = v;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(sequence, "Séquence incorrecte. Nombre inconnu : \n \"" + t + "\"", "Erreur lors du décodage de la séquence", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int index = algorithme.getSelectedIndex();
        String nomAlgo = algorithme.getItemAt(index);

        int nbPages = Integer.parseInt(nbPagesField.getText());

        AlgoDefautDePage algo = null;
        switch (nomAlgo) {
            case "PAPS":
                algo = new AlgoPaps(nbPages, val);
                break;
            case "LRU":
                algo = new AlgoLru(nbPages, val);
                break;
            case "LFU":
                algo = new AlgoLfu(nbPages, val);
                break;
        }

        if(algo != null) {
            algo.demarrer();
            new FenetreResultat(algo);
        }
    }
}
