package net.Fachpersonal;

import java.util.ArrayList;

public class Knoten {
    private final int name;
    private final int x;
    private final int y;

    private boolean besucht;

    private ArrayList<Kante> neighbors;

    public Knoten(int name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        neighbors = new ArrayList<>();
        besucht = false;
    }

    public void besucht() {besucht = true;}
    public boolean isBesucht() {return besucht;}

    public void clearBesucht() {
        besucht = false;
    }

    public ArrayList<Kante> getNeighbors() {return neighbors;}

    public int getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
