package net.Fachpersonal;

public class Kante {

    private final int x0;
    private final int y0;
    private final int x1;
    private final int y1;
    public Kante(int x, int y, int x1, int y1) {
        x0 = x;
        this.x1 = x1;
        y0= y;
        this.y1=y1;
    }

    public int getX0() {
        return x0;
    }

    public int getY0() {
        return y0;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }
}
