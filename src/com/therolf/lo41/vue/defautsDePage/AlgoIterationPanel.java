package com.therolf.lo41.vue.defautsDePage;

import com.therolf.lo41.modele.defautsDePage.AlgoDefautDePage;
import com.therolf.lo41.modele.defautsDePage.AlgoIteration;
import com.therolf.lo41.vue.JTableUtilities;
import com.therolf.lo41.vue.JRetourLabel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class AlgoIterationPanel extends JPanel {

    private final AlgoDefautDePage algo;
    private final GridBagConstraints gbc;

    public AlgoIterationPanel(AlgoDefautDePage algo)  {
        this.algo = algo;
        // setBorder(PANEL_BORDER);

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
    }

    public void ajouter(int indiceSequence, String iterationNom) {
        AlgoIteration iterationAlgo = algo.getChangements().get(indiceSequence);

        String s = "Itération " + iterationNom;
        s += ": nombre " + iterationAlgo.getNouveauNombre() + " ";
        s += ": \n" + iterationAlgo.getRaison();

        if(iterationAlgo.isChangement() && !iterationAlgo.getAncienNombre().equals(""))
                s += " | " + iterationAlgo.getAncienNombre() + " => " + iterationAlgo.getNouveauNombre();

        JRetourLabel label = new JRetourLabel( s, JLabel.LEFT);
        label.setBorder(JRetourLabel.LABEL_BORDER);
        add(label, gbc);

        String nouveau = iterationAlgo.getNouveauNombre();
        JTable table = new JTable(algo.getResultatAIndice(indiceSequence), algo.getSequenceAIndice(indiceSequence)){
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);

                if(algo.getChangements().get(col).getNouveauNombre().equals(getModel().getValueAt(row, col))) {
                    if(algo.getChangements().get(col).isChangement())
                        comp.setBackground(Color.RED);
                    else
                        comp.setBackground(Color.GREEN);
                } else {
                    comp.setBackground(Color.WHITE);
                }

                return comp;
            }
        };
        resizeColumnWidth(table);
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        table.getTableHeader().setReorderingAllowed(false);
        table.setOpaque(true);
        table.setBackground(this.getBackground());

        add(table.getTableHeader(), gbc);
        add(table, gbc);
    }

    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
            columnModel.getColumn(column).setMaxWidth(width);
        }
    }
}
