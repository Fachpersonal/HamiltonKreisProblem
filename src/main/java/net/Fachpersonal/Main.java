package net.Fachpersonal;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Knoten> KN;
    public static ArrayList<Kante> kanten;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        KN = new ArrayList<>();
        KN.add(new Knoten("A", 50,50));
        KN.add(new Knoten("B", 150, 50));
        KN.add(new Knoten("C", 50,150));
        KN.add(new Knoten("D", 75,75));
        kanten = new ArrayList<>();
        kanten.add(new Kante(KN.get(0), KN.get(1)));
        kanten.add(new Kante(KN.get(0), KN.get(2)));
        kanten.add(new Kante(KN.get(1), KN.get(2)));
        kanten.add(new Kante(KN.get(1), KN.get(3)));
        kanten.add(new Kante(KN.get(2), KN.get(3)));
        new Window();
    }
}