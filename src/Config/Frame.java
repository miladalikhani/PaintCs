package Config;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        Surface surface = new Surface();
        this.add(surface);
        setTitle("PaintCS");
        setSize(1300, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
