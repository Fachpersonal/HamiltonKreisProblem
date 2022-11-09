package net.Fachpersonal;

import javax.swing.*;

public class Window extends JFrame {

    private final String TITLE = "Hamilton Kreis Problem - v.0.1";
    public Window() {
        this.setIconImage(new ImageIcon("C:\\Users\\falscherIdiot\\Pictures\\Downloads\\HamiltonKreisProblem.jpg").getImage());
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setSize(500,500);
        this.add(new Graph());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
