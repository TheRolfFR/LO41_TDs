package com.therolf.lo41.modele.defautsDePage;

public class AlgoLru extends AlgoDefautDePage {
    public AlgoLru(int nbPages, Integer[] sequence) throws NumberFormatException {
        super("LRU", nbPages, sequence);
    }

    @Override
    public void demarrer() {
        Integer[] seq = getSequence();
        int lgSequence = seq.length;
        int nbPages = getNbPages();

        AlgoIteration it; // = null
        for(int i = 0; i < lgSequence; ++i) {
            copierLignePrecedente(i);
            if(i < nbPages) {
                resultat[i][i] = "" + seq[i];
                it = new AlgoIteration("Nouveau nombre", "", "" + seq[i]);

                ++nbDefautsDePage;
            } else {
                if(!seqPrecContient(i, seq[i])) {
                    int age = 0;
                    int page = 0;
                    int j, a;
                    for(int p = 0; p < nbPages; ++p) {
                        String nombre = resultat[p][i];
                        j = i-1; // Ppur j allant de i-1
                        a = 0;

                        // à 0, et tant que nombre n'a pas eu de changement
                        while(j > -1 && !changements.get(j).getNouveauNombre().equals(nombre)) {
                            ++a; // on agumente l'age temporaire
                            --j; // on diminue j
                        }

                        if(a > age) {
                            age = a;
                            page = p;
                        }
                    }

                    it = new AlgoIteration(seq[page] + " le plus vieux", resultat[page][i], "" + seq[i]);
                    resultat[page][i] = "" + seq[i];

                    ++nbDefautsDePage;
                } else {
                    it = new AlgoIteration("" + seq[i]); // pas de changements
                }
            }

            changements.add(it);
        }
    }
}
