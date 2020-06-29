package com.therolf.lo41.modele.defautsDePage;

import java.util.ArrayList;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public abstract class AlgoDefautDePage {

    private final String nomAlgo;
    private final int nbPages;
    private final Integer[] sequence;

    protected ArrayList<AlgoIteration> changements = new ArrayList<>();
    protected int nbDefautsDePage = 0;
    protected String[][] resultat; // = null

    public String getNom() {
        return nomAlgo;
    }
    public int getNbPages() {
        return nbPages;
    }
    public Integer[] getSequence() {
        return sequence;
    }

    public ArrayList<AlgoIteration> getChangements() {
        return changements;
    }
    public String[][] getResultat() {
        return resultat;
    }
    public int getNbDefautsDePage() {
        return nbDefautsDePage;
    }

    public AlgoDefautDePage(String nomAlgo, int nbPages, Integer[] sequence) throws NumberFormatException {
        if(nbPages < 1)
            throw new NumberFormatException("Number must be greater than 0");

        this.nomAlgo = nomAlgo;
        this.nbPages = nbPages;
        this.sequence = sequence;

        resultat = new String[nbPages][sequence.length];
        remplirPremiereLigne();
    }

    public abstract void demarrer();

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected final boolean seqPrecContient(int indiceSequence, int nb) {
        int i = 0;

        while(i < nbPages && resultat[i][indiceSequence].charAt(0) != ("" + nb).charAt(0))
            ++i;

        return i < nbPages;
    }

    protected final void remplirPremiereLigne() {
        for(int i = 0; i < nbPages; ++i) {
            resultat[i][0] = "";
        }
    }

    protected final void copierLignePrecedente(int indiceSequence) {
        if(indiceSequence < 1)
            return;

        for(int i = 0; i < nbPages; ++i) {
            resultat[i][indiceSequence] = resultat[i][indiceSequence-1];
        }
    }

    public final Integer[] getSequenceAIndice(int indiceSequence) {
        indiceSequence += 1;

        Integer[] seq = new Integer[indiceSequence];

        System.arraycopy(sequence, 0, seq, 0, indiceSequence);

        return seq;
    }

    public final String[][] getResultatAIndice(int indiceSequence) {
        indiceSequence += 1;

        String[][] res = new String[nbPages][indiceSequence];

        for(int i = 0; i < nbPages; ++i) {
            System.arraycopy(resultat[i], 0, res[i], 0, indiceSequence);
        }

        return res;
    }
}
