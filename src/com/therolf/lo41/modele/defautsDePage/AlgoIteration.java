package com.therolf.lo41.modele.defautsDePage;

public class AlgoIteration {
    private boolean changement;

    private String raison = "Pas de changement";
    private String ancienNombre = "";
    private String nouveauNombre; // = ""

    public boolean isChangement() {
        return changement;
    }

    public String getRaison() {
        return raison;
    }

    public String getAncienNombre() {
        return ancienNombre;
    }

    public String getNouveauNombre() {
        return nouveauNombre;
    }

    public AlgoIteration(String nouveauNombre) {
        this.changement = false;
        this.nouveauNombre = nouveauNombre;
    }

    public AlgoIteration(String raison, String ancienNombre, String nouveauNombre) {
        this.changement = true;
        this.raison = raison;
        this.ancienNombre = ancienNombre;
        this.nouveauNombre = nouveauNombre;
    }
}
