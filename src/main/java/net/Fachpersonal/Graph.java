package net.Fachpersonal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Graph extends JPanel {
    public static boolean find = false;
    private final int SCALE = 25;
    private Point pS = null;
    private Point pE = null;

    private int[] path;

    public Graph() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pS = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                System.out.println("pS :" + pS.x + " " + pS.y);
                pE = e.getPoint();
                System.out.println("pE :" + pE.x + " " + pE.y);
                if (pS.x != pE.x || pS.y != pE.y) {
                    System.out.println("IM IN!");
                    pE = e.getPoint();
                    Knoten k1 = null;
                    Knoten k2 = null;
                    for (Knoten k : Main.KN) {
                        if (isBetween(k.getX(), pS.x - SCALE, pS.x + SCALE) && isBetween(k.getY(), pS.y - SCALE, pS.y + SCALE)) {
                            k1 = k;
                            System.out.println("k1 found");
                        } else if (isBetween(k.getX(), pE.x - SCALE, pE.x + SCALE) && isBetween(k.getY(), pE.y - SCALE, pE.y + SCALE)) {
                            k2 = k;
                            System.out.println("k2 found");
                        }
                    }
                    if (k1 != null && k2 != null) {
                        Kante kant = new Kante(k1, k2);
                        Main.kanten.add(kant);
                        k1.getNeighbors().add(kant);
                        k2.getNeighbors().add(kant);
                        System.out.println("Created KANTE!");
                        repaint();
                    }
                } else {
                    Main.KN.add(new Knoten(Main.KN.size(), pS.x - (SCALE / 2), pS.y - (SCALE / 2)));
                    System.out.println("Created KNOTEN!");
                    repaint();
                }
            }
        });
    }

    private boolean isBetween(int x, int v1, int v2) {
        return (x >= v1 && x <= v2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Kante k : Main.kanten) {
            g.setColor(Window.colorPalette[4]);
            g.drawLine(k.getKnotenA().getX() + (SCALE / 2), k.getKnotenA().getY() + (SCALE / 2), k.getKnotenB().getX() + (SCALE / 2), k.getKnotenB().getY() + (SCALE / 2));
        }
        for (Knoten k : Main.KN) {
            g.setColor(Window.colorPalette[2]);
            if (k.getNeighbors().size() >= 2) {
                g.setColor(Window.colorPalette[3]);
            }
            g.fillOval(k.getX(), k.getY(), SCALE, SCALE);
            g.drawString(k.getName() + "", k.getX(), k.getY());
        }
        if (find) {
            for (Knoten k : Main.KN) {
                if (k.getNeighbors().size() < 2) {
                    System.out.println("NOT POSSIBLE '" + k.getName() + "' only has " + k.getNeighbors());
                }
            }
            System.out.println(HamCircl());

            find = false;
        }
    }

    private boolean HamCircl() {
        int V = Main.KN.size();

        path = new int[V];

        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }


        solvHamCircl(0, Main.KN.get(0));
    }

    private void solvHamCircl(int pos, Knoten k) {
        for (Kante _ke : k.getNeighbors()) {
            Knoten _k = (k.getName() == _ke.getKnotenA().getName() ? _ke.getKnotenB() : _ke.getKnotenA());
            boolean x = false;
            for (int i : path) {
                if (i == _k.getName()) {
                    if(x) {
                        path[pos] = -1;
                        return;
                    }
                    x = true;
                }
            }
            path[pos] = _k.getName();
            solvHamCircl(pos + 1, _k);
        }
    }

    /*
    private boolean solvHamCircl(int pos, Knoten k) {
        if(pos+1 > path.length) {
            for (int i = 0; i < path.length; i++) {
                if(path[i] == -1) {
                    break;
                }
            }
            if(path[pos] == Main.KN.get(0).getName()) {
                return true;
            }
            return false;
        }
        ArrayList<Kante> kn = k.getNeighbors();
        for(Kante _kn : kn) {
            Knoten _Knoten = _kn.getKnotenB();
            if(pos != 0 && _Knoten == Main.KN.get(path[pos-1])) {
                continue;
            }
            for (int i = 0; i < path.length; i++) {
                if(_Knoten.getName() == path[i] && i != pos-1) {
                    return false;
                }
            }
            path[pos] = _Knoten.getName();
            if (solvHamCircl(pos+1, _Knoten)) {
                return true;
            } else {
                path[pos] = -1;
            }
        }

        return false;
    }*/
}
