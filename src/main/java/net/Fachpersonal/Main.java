package net.Fachpersonal;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Knoten> KN;
    public static ArrayList<Kante> kanten;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        KN = new ArrayList<>();
        kanten = new ArrayList<>();
        new Thread(new Window()).start();
    }
}