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

    private Knoten leadsTo(Knoten k) {
        if (k.getName() == knotenA.getName()) return knotenA;
        if (k.getName() == knotenB.getName()) return knotenB;
        return null;
    }

    public void besucht() {besucht = true;}
    public boolean isBesucht() {return besucht;}

    public void clearBesucht() {
        besucht = false;
    }

    public Knoten getKnotenA() {
        return knotenA;
    }

    public Knoten getKnotenB() {
        return knotenB;
    }
}
