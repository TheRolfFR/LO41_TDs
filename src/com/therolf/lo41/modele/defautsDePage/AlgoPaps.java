package com.therolf.lo41.modele.defautsDePage;

public class AlgoPaps extends AlgoDefautDePage {
    public AlgoPaps(int nbPages, Integer[] sequence) throws NumberFormatException {
        super("PAPS", nbPages, sequence);
    }

    @Override
    public void demarrer() {
        Integer[] seq = getSequence();
        int lgSequence = seq.length;
        int nbPages = getNbPages();

        int offset = 0;

        AlgoIteration it; // = null
        for(int i = 0; i < lgSequence; ++i) {
            copierLignePrecedente(i);
            if(i < nbPages) {
                resultat[offset][i] = "" + seq[i];
                it = new AlgoIteration("Nouveau nombre", "", "" + seq[i]);

                offset = (offset + 1) % nbPages;
                ++nbDefautsDePage;
            } else {
                if(!seqPrecContient(i, seq[i])) {
                    it = new AlgoIteration("Nombre pas dans la liste", "" + resultat[offset][i], "" + seq[i]);
                    resultat[offset][i] = "" + seq[i];

                    offset = (offset + 1) % nbPages;
                    ++nbDefautsDePage;
                } else {
                    it = new AlgoIteration("" + seq[i]); // pas de changements
                }
            }

            changements.add(it);
        }
    }
}
