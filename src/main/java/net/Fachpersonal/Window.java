package net.Fachpersonal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame implements Runnable {

    public static Color[] colorPalette;

    public Window() {
        colorPalette = new Color[] {
                new Color(239, 245, 245),
                new Color(214,228,229),
                new Color(73,113,116),
                new Color(235,100,64),
                new Color(57,62,70),
                new Color(255, 0, 0)
        };
        //https://colorhunt.co/palette/eff5f5d6e4e5497174eb6440
    }
    @Override
    public void run() {
        try {
            this.setIconImage(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResource("/HamiltonKreisProblem.jpg")))).getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String TITLE = "Hamilton Kreis Problem - v.0.2";
        this.setTitle(TITLE);
        this.setResizable(true);
        this.setSize(500, 500);
        this.add(new Graph());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(colorPalette[0]);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R) { // REPAINT
                    repaint();
                    System.out.println("REAP");
                } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){ // CLOSE PROGRAM
                    System.exit(0x16);
                } else if(e.getKeyCode() == KeyEvent.VK_C) { // CLEAR BOARD
                    Main.kanten.clear();
                    Main.knoten.clear();
                    repaint();
                    System.out.println("Cleared Board");
                } else if(e.getKeyCode() == KeyEvent.VK_F) { // FIND PATH
                    //System.out.println("test");
                    Graph.find = true;
                    Graph.found = false;
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setVisible(true);
    }
}
