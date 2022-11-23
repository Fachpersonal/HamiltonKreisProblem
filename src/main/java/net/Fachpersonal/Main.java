package net.Fachpersonal;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Knoten> knoten;
    public static ArrayList<Kante> kanten;

    public static boolean simutaltion;
    public static int sim_knotenCount;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        knoten = new ArrayList<>();
        kanten = new ArrayList<>();
        simutaltion = false;
        sim_knotenCount = 40;
        if (simutaltion) {
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
        new Graph().hamCircle();
    }
}