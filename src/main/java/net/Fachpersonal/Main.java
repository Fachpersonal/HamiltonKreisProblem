package net.Fachpersonal;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static ArrayList<Knoten> knoten;
    public static ArrayList<Kante> kanten;

    public static boolean simulation;
    public static int sim_knotenCount;
    public static boolean calculateAllPossibleSolutions;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        knoten = new ArrayList<>();
        kanten = new ArrayList<>();

        /**
         * calculateAllPossibleSolutions: alle möglichen pfade ausgeben?
         * set simulation: true, um simulation zu starten
         * sim_knotenCount: anzahl der zu simulierenden Knoten -> größer als 7 dauert zu lange
         */
        calculateAllPossibleSolutions = true;
        simulation = true;
        sim_knotenCount = 8;
        if (simulation) {
            simulate();
            return;
        }
        new Thread(new Window()).start();
    }

    public static Knoten getKnotenByName(int name) {
        for (Knoten all : knoten) {
            if (all.getName() == name) return all;
        }
        return null;
    }

    public static void simulate() {
        for (int i = 0; i < sim_knotenCount; i++) {
            knoten.add(new Knoten(i));
        }
        //System.out.println(knoten.size());
        for (Knoten all : knoten) {
            ArrayList<Knoten> foo = new ArrayList<>(knoten);
            all.setNachbarn(foo);
            //System.out.println(all.getNachbarn().size());
        }
        if (calculateAllPossibleSolutions) {
            giveAllPossibleSolutions();
            return;
        }
        new Graph().hamCircle();
    }

    public static void giveAllPossibleSolutions() {
        Graph g = new Graph();

        g.sys_time = System.currentTimeMillis();

        while (g.hamCircle()) {
            g.foundPaths.add(Arrays.copyOf(g.path, g.path.length));
        }
        System.out.println("------------------------------");
        for (int[] all : g.foundPaths) {
            g.drawPath(all);
        }
        System.out.println("found paths: " + g.foundPaths.size());

        //time to find all paths
        double duration = System.currentTimeMillis() - g.sys_time;
        System.out.println("Duration: " + duration + "ms ~ " + duration / 1000 + "s ~ " + duration / 60000 + "min");
    }
}