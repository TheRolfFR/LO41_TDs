package com.therolf.lo41.modele.defautsDePage;

import java.util.HashMap;

public class AlgoLfu extends AlgoDefautDePage {
    public AlgoLfu(int nbPages, Integer[] sequence) throws NumberFormatException {
        super("LFU", nbPages, sequence);
    }

    @Override
    public void demarrer() {
        Integer[] seq = getSequence();
        int lgSequence = seq.length;
        int nbPages = getNbPages();

        HashMap<Integer, Integer> freq = new HashMap<>();

        AlgoIteration it; // = null
        for(int i = 0; i < lgSequence; ++i) {
            copierLignePrecedente(i);

            if(i < nbPages) {
                String nvRes = getNouveauNombre(freq, seq[i]);
                resultat[i][i] = nvRes;
                it = new AlgoIteration("Nouveau nombre", "", nvRes);

                ++nbDefautsDePage;
            } else {
                if(!seqPrecContient(i, seq[i])) {
                    // lfu
                    int freqmin = -1;
                    int page = 0;
                    int tmpfreq;
                    for(int p = 0; p < nbPages; ++p) {
                        tmpfreq = getFrequence(freq, Integer.parseInt(resultat[p][i].substring(0, 1)));
                        if(freqmin == -1 || tmpfreq < freqmin) {
                            freqmin = tmpfreq;
                            page = p;
                        }
                    }

                    boolean conflitMin = false;
                    int p = 0;
                    while (p < nbPages && !conflitMin) {
                        if(p != page && getFrequence(freq, Integer.parseInt(resultat[p][i].substring(0, 1))) == freqmin) {
                            conflitMin = true;
                        }
                        ++p;
                    }

                    String nvRes;
                    if(conflitMin) {
                        // lru sur freq min
                        int age = 0;
                        page = -1;
                        int j, a;
                        for(p = 0; p < nbPages; ++p) {
                            if(freqmin == getFrequence(freq, Integer.parseInt(resultat[p][i].substring(0, 1)))) {
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
                        }

                        nvRes = getNouveauNombre(freq, seq[i]);
                        it = new AlgoIteration("Conflit freq min: " + resultat[page][i] + " le plus vieux", resultat[page][i], nvRes);
                    } else {
                        nvRes = getNouveauNombre(freq, seq[i]);
                        it = new AlgoIteration(resultat[page][i] + " Le moins utilisé", resultat[page][i], nvRes);
                    }

                    resultat[page][i] = nvRes;

                    ++nbDefautsDePage;
                } else {
                    int p = 0;
                    while (p < nbPages && resultat[p][i].charAt(0) != ("" + seq[i]).charAt(0))
                        ++p;

                    String nvRes = getNouveauNombre(freq, seq[i]);
                    it = new AlgoIteration(nvRes); // pas de changements
                    resultat[p][i] = nvRes;
                }
            }

            changements.add(it);
            augmenterFrequence(freq, seq[i]);
        }
    }

    private String getNouveauNombre(HashMap<Integer, Integer>frequences, int nb) {
        return nb + "-" + (getFrequence(frequences, nb)+1);
    }

    private int getFrequence(HashMap<Integer, Integer>frequences, int nb) {
        return frequences.getOrDefault(nb, 0);
    }

    private void augmenterFrequence(HashMap<Integer, Integer>frequences, int nb) {
        frequences.put(nb, getFrequence(frequences, nb)+1);
    }
}
