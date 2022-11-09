package net.Fachpersonal;

public class Kante {

    private Knoten knotenA;
    private Knoten knotenB;

    public Kante(Knoten k1, Knoten k2) {
        this.knotenA = k1;
        this.knotenB = k2;
    }

    public Knoten getKnotenA() {
        return knotenA;
    }

    public Knoten getKnotenB() {
        return knotenB;
    }
}
