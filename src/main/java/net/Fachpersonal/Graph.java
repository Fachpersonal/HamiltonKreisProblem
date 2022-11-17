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
                        Main.kanten.add(new Kante(k1.getX(), k1.getY(), k2.getX(), k2.getY()));
                        k1.getNachbarn().add(k2);
                        k2.getNachbarn().add(k1);
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
            g.drawLine(k.getX0() + (SCALE / 2), k.getY0() + (SCALE / 2), k.getX1() + (SCALE / 2), k.getY1() + (SCALE / 2));
        }
        for (Knoten k : Main.KN) {
            g.setColor(Window.colorPalette[2]);
            if (k.getNachbarn().size() >= 2) {
                g.setColor(Window.colorPalette[3]);
            }
            g.fillOval(k.getX(), k.getY(), SCALE, SCALE);
            g.drawString(k.getName() + "", k.getX(), k.getY());
        }
        if (find) {
            for (Knoten k : Main.KN) {
                if (k.getNachbarn().size() < 2) {
                    System.out.println("NOT POSSIBLE '" + k.getName() + "' only has " + k.getNachbarn().size() + " neighbor");
                    return;
                }
            }
            _HamCircl();
            find = false;
        }
    }

    private void _HamCircl() {
        int V = Main.KN.size();

        path = new int[V];

        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        if (solvHC(0, Main.KN.get(0))) {
            System.out.print("path : 0 -> ");
            for (int x : path) {
                System.out.print(x + " -> ");
            }
        } else {
            System.out.println("Kein Pfad gefunden!");
        }
    }

    private boolean solvHC(int pos, Knoten k) {
        // Pick n' getNachbarn()
        for (Knoten _k: k.getNachbarn()) {
            // pruefe
            if(_k.getName() == 0) {
                if (pos!= path.length-1) { // BSP [5] pos: 4 nachbar.name true
                    continue;
                }
                path[pos] = _k.getName();
                return true;
            }
            // pruefe
            if(checkIfExists(_k.getName()))
                continue;

            path[pos] = _k.getName();
            // wenn eins true alles true
            // wenn eins false solang zurueck und neue nachbar nehmen // wenn diese auch false dann wieder!
            if (solvHC(pos + 1, _k)) {
                return true;
            }
            path[pos] = -1;
        }
        return false;
    }

    private boolean checkIfExists(int name) {
        for(int x : path) {
            if(x == name) {
                return true;
            }
        }
        return false;
    }
}