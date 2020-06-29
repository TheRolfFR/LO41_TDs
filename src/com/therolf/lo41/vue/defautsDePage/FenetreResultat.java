package com.therolf.lo41.vue.defautsDePage;

import com.therolf.lo41.vue.JRetourLabel;
import com.therolf.lo41.modele.defautsDePage.AlgoDefautDePage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class FenetreResultat extends JFrame {

    private static final Dimension DIMENSIONS = new Dimension(800, 500);

    public FenetreResultat(AlgoDefautDePage algo) {
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Résultats défauts de page " + algo.getNom());
        setMinimumSize(DIMENSIONS);
        setPreferredSize(DIMENSIONS);

        this.setLayout(new BorderLayout(10, 10));

        String s = "Résultats de l'algo " + algo.getNom() + " pour la séquence :\n ";
        s += Arrays.toString(algo.getSequence()) + "\n";
        s += "Nombre de défauts de page : " + algo.getNbDefautsDePage();
        JRetourLabel texte = new JRetourLabel(s);
        texte.setBorder(JRetourLabel.LABEL_BORDER);
        this.getContentPane().add(texte, BorderLayout.NORTH);

        AlgoIterationPanel insideScroll = new AlgoIterationPanel(algo);
        insideScroll.setBorder(JRetourLabel.LABEL_BORDER);
        for(int i = 0; i < algo.getChangements().size(); ++i) {
            insideScroll.ajouter( i, "" + (i+1));
        }

        JScrollPane scrollPane = new JScrollPane(insideScroll);

        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
