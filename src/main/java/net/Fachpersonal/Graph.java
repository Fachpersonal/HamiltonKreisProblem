package net.Fachpersonal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Graph extends JPanel {
    public static boolean find = false;
    public static boolean found = false;
    private final int SCALE = 25;
    private Point pointPressed = null;
    private Point pointReleased = null;

    private int[] path;
    private Graphics g;

    public Graph() {
        Button b_find = new Button("Find Path");
        Button b_clear = new Button("Clear");
        //b_find.setBounds(100,100,80,40);
        this.add(b_find);
        this.add(b_clear);
        b_find.addActionListener(e -> {
            find = true;
            found = false;
            repaint();
        });
        b_clear.addActionListener(e -> {
            find = true;
            found = false;
            Main.kanten.clear();
            Main.knoten.clear();
            repaint();
        });


        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                pointPressed = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                System.out.println("pS :" + pointPressed.x + " " + pointPressed.y);
                pointReleased = e.getPoint();
                System.out.println("pE :" + pointReleased.x + " " + pointReleased.y);
                if (pointPressed.x != pointReleased.x || pointPressed.y != pointReleased.y) {
                    System.out.println("IM IN!");
                    pointReleased = e.getPoint();
                    Knoten k1 = null;
                    Knoten k2 = null;
                    for (Knoten k : Main.knoten) {
                        if (isBetween(k.getX(), pointPressed.x - SCALE, pointPressed.x + SCALE) && isBetween(k.getY(), pointPressed.y - SCALE, pointPressed.y + SCALE)) {
                            k1 = k;
                            System.out.println("k1 found");
                        } else if (isBetween(k.getX(), pointReleased.x - SCALE, pointReleased.x + SCALE) && isBetween(k.getY(), pointReleased.y - SCALE, pointReleased.y + SCALE)) {
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
                    Main.knoten.add(new Knoten(Main.knoten.size(), pointPressed.x - (SCALE / 2), pointPressed.y - (SCALE / 2)));
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
        this.g = g;
        for (Kante k : Main.kanten) {
            g.setColor(Window.colorPalette[4]);
            g.drawLine(k.getX0() + (SCALE / 2), k.getY0() + (SCALE / 2), k.getX1() + (SCALE / 2), k.getY1() + (SCALE / 2));
        }
        //gefundenen pfad hervorheben
        if (path != null && found) {
            for (int i = -1; i < path.length; i++) {
                Knoten knoten = Main.getKnotenByName(i == -1 ? 0 : path[i]);
                for (Knoten all : knoten.getNachbarn()) {
                    if (i + 1 < path.length) {
                        if (all.getName() == path[i + 1]) {
                            g.setColor(Window.colorPalette[5]);
                            Graphics2D g2 = (Graphics2D) g;
                            g2.setStroke(new BasicStroke(3));
                            g2.setStroke(new BasicStroke(3));
                            //g2.drawLine();   //thick
                            g2.drawLine(knoten.getX() + (SCALE / 2), knoten.getY() + (SCALE / 2), all.getX() + (SCALE / 2), all.getY() + (SCALE / 2));
                            g.drawString(""+path, 500,500);
                        }
                    }
                }

            }
        }
        for (Knoten k : Main.knoten) {
            g.setColor(Window.colorPalette[2]);
            if (k.getNachbarn().size() >= 2) {
                g.setColor(Window.colorPalette[3]);
            }
            g.fillOval(k.getX(), k.getY(), SCALE, SCALE);
            g.drawString(k.getName() + "", k.getX(), k.getY());
        }
        if (find) {
            for (Knoten k : Main.knoten) {
                if (k.getNachbarn().size() < 2) {
                    System.out.println("NOT POSSIBLE '" + k.getName() + "' only has " + k.getNachbarn().size() + " neighbor");
                    return;
                }
            }
            hamCircle();
            find = false;
        }
    }

    private void hamCircle() {
        int anzahlKnoten = Main.knoten.size();

        path = new int[anzahlKnoten];

        for (int i = 0; i < anzahlKnoten; i++) {
            path[i] = -1;
        }

        if (Main.knoten.size() != 0) {
            if (solveHC(0, Main.knoten.get(0))) {
                found = true;
                System.out.print("path : 0 -> ");
                for (int x : path) {
                    System.out.print(x == 0 ? x + "\n" : x + " -> ");
                    repaint();


                }
                return;
            }
        }
        System.out.println("Keinen Pfad gefunden!");
    }

    private boolean solveHC(int pos, Knoten k) {
        // Pick n' getNachbarn()
        for (Knoten nachbar : k.getNachbarn()) {
            // pruefe
            if (nachbar.getName() == 0) {
                if (pos != path.length - 1) { // BSP [5] pos: 4 nachbar.name true
                    continue;
                }
                path[pos] = nachbar.getName();
                return true;
            }
            // pruefe
            if (checkIfExists(nachbar.getName()))
                continue;

            path[pos] = nachbar.getName();
            // wenn eins true alles true
            // wenn eins false solang zurueck und neue nachbar nehmen // wenn diese auch false dann wieder!
            if (solveHC(pos + 1, nachbar)) {
                return true;
            }
            path[pos] = -1;
        }
        return false;
    }

    private boolean checkIfExists(int name) {
        for (int x : path) {
            if (x == name) {
                return true;
            }
        }
        return false;
    }
}