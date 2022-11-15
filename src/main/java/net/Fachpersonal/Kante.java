package net.Fachpersonal;

public class Kante {

    private final Knoten knotenA;
    private final Knoten knotenB;

    private boolean besucht;

    public Kante(Knoten k1, Knoten k2) {
        this.knotenA = k1;
        this.knotenB = k2;
        besucht = false;
    }

    public void besucht() {besucht = !besucht;}
    public boolean getBesucht() {return besucht;}

    public Knoten getKnotenA() {
        return knotenA;
    }

    public Knoten getKnotenB() {
        return knotenB;
    }
}
