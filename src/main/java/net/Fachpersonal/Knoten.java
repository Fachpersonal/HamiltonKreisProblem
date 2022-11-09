package net.Fachpersonal;

public class Knoten {
    private final String name;
    private final int x;
    private final int y;

    public Knoten(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
