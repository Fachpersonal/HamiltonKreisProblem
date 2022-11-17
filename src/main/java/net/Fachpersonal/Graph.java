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
    private int[] _path;

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
                    System.out.println("NOT POSSIBLE '" + k.getName() + "' only has " + k.getNeighbors().size() + " neighbor");
                    return;
                }
            }
//            printHamCircle();
            _HamCircl();
            find = false;
        }
    }

    private void _HamCircl() {
        int V = Main.KN.size();

        _path = new int[V];

        for (int i = 0; i < V; i++) {
            _path[V] = -1;
        }

        solvHC(0,Main.KN.get(0));
    }

    private boolean solvHC(int pos,Knoten k) {
        for (Kante kn : k.getNeighbors()) {
            Knoten _k = (k.getName() == kn.getKnotenA().getName() ? kn.getKnotenB() : kn.getKnotenA());
            if(_k.getName() == 0 && check(0)) {
                return true;
            }
            _path[pos] = _k.getName();
            if(solvHC(pos+1,_k)) {
                return true;
            }
        }
        return false;
    }
    private boolean check(int x) {
        for (int i = 0; i < _path.length-1; i++) {
            if(_path[i] == -1)
                return false;
        }
        return _path[_path.length-1] == -1 && x==0;
    }

    private void printHamCircle() {
        int anzahlKnoten = Main.KN.size();

        System.out.println("-------------------------");
        System.out.println("Anzahl Knoten: " + anzahlKnoten);

        path = new int[anzahlKnoten + 1];

        for (int i = 1; i <= anzahlKnoten; i++) {
            path[i] = -1;
        }

        //Main.KN.get(0).besucht();
        path = solveHamCircle(path, 1, -1, Main.KN.get(0));

        System.out.println("path: ");
        for (int i : path) {
            System.out.print(i);
        }
        System.out.println();
    }

    public void testPrint(int[] path) {
        System.out.println("test printed path: ");
        for (int i : path) {
            System.out.print(i);
        }
        System.out.println();
    }

    /**
     * @param path
     * @param step
     * @param param -1=normal ; other number = previous path
     * @param k
     * @return
     */
    private int[] solveHamCircle(int[] path, int step, int param, Knoten k) {
        System.out.println("Step: " + step + "; Knoten: " + k.getName());
        testPrint(path);


        //neuen Test-Path anlegen
        int[] localPath = path;

        if (step > path.length) return null;

        //teste jede Kante des Knotens
        for (Kante nKante : k.getNeighbors()) {
            if (!nKante.isBesucht()) {
                //bekomme nachbar knoten
                Knoten n = (k.getName() == nKante.getKnotenA().getName() ? nKante.getKnotenB() : nKante.getKnotenA());
                if (!n.isBesucht()) {
                    if (n.getName() != param) {
                        localPath[step] = n.getName();
                        nKante.besucht();
                        n.besucht();

                        //teste, ob HamCircle erfolgreich geschlossen wurde
                        if (localPath[step] == k.getName() && step != 0) {
                            return localPath;
                        }
                        solveHamCircle(localPath, step + 1, -1, n);
                    }
                }
            }
        }


        int[] previousPath = path;
        previousPath[step - 1] = -1;
        for (Kante nKante : k.getNeighbors()) {
            nKante.besucht();
            nKante.getKnotenA().besucht();
            nKante.getKnotenB().besucht();
        }
        solveHamCircle(previousPath, step - 1, k.getName(), getKnotenByName(path[step - 1]));

        return null;
    }

    public Knoten getKnotenByName(int name) {
        for (Knoten k : Main.KN) {
            if (k.getName() == name) return k;
        }
        return null;
        }
    }