package net.Fachpersonal;

import java.util.ArrayList;

public class Knoten {
    private final int name;
    private final int X;
    private final int Y;
    private ArrayList<Knoten> nachbarn;

    public Knoten(int name, int x, int y) {
        this.name = name;
        this.X = x;
        this.Y = y;
        nachbarn = new ArrayList<>();
    }

    public Knoten(int name) {
        this.name = name;
        this.X = -1;
        this.Y = -1;
        nachbarn = new ArrayList<>();
    }

    public int getName() {
        return name;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public ArrayList<Knoten> getNachbarn() {
        return nachbarn;
    }

    public void setNachbarn(ArrayList<Knoten> nachbarn) {
        if (nachbarn.contains(Main.getKnotenByName(name))) {
            nachbarn.remove(this);
        }
        this.nachbarn = nachbarn;
    }
}
