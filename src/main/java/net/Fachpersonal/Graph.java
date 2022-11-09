package net.Fachpersonal;

import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {

    public Graph() {
        this.setSize(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D gr = (Graphics2D) g;
        for (Kante k : Main.kanten) {
            gr.setColor(Color.BLACK);
            gr.drawLine(k.getKnotenA().getX()+7, k.getKnotenA().getY()+7, k.getKnotenB().getX()+7, k.getKnotenB().getY()+7);
            gr.setColor(Color.RED);
            gr.fillOval(k.getKnotenA().getX(),k.getKnotenA().getY(),15,15);
            gr.drawString(k.getKnotenA().getName(), k.getKnotenA().getX(),k.getKnotenA().getY());
            gr.fillOval(k.getKnotenB().getX(),k.getKnotenB().getY(),15,15);
            gr.drawString(k.getKnotenB().getName(), k.getKnotenB().getX(),k.getKnotenB().getY());
        }
    }
}
