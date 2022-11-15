package net.Fachpersonal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {

    public static Color[] colorPalette;

    private final int TICKS_PER_SECOND = 2;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAMESKIP = 5;

    public Window() {
        colorPalette = new Color[] {
                new Color(239, 245, 245),
                new Color(214,228,229),
                new Color(73,113,116),
                new Color(235,100,64),
                new Color(57,62,70)
        };
        //https://colorhunt.co/palette/eff5f5d6e4e5497174eb6440
    }

    private final int width = 500;
    private final int height = 500;
    private final String TITLE = "Hamilton Kreis Problem - v.0.2";
    @Override
    public void run() {
        this.setIconImage(new ImageIcon("C:\\Users\\falscherIdiot\\Pictures\\Downloads\\HamiltonKreisProblem.jpg").getImage());
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setSize(width, height);
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
                    Main.KN.clear();
                    repaint();
                    System.out.println("Cleared Board");
                } else if(e.getKeyCode() == KeyEvent.VK_F) { // FIND PATH
                    Graph.find = !Graph.find;
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setVisible(true);
    }
}
