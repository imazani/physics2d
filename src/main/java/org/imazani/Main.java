package org.imazani;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Physics2D – Hello Window");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setContentPane(new Main());
            f.setSize(800, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Simple proof-of-life render
        g.setFont(g.getFont().deriveFont(Font.BOLD, 18f));
        g.drawString("Environment OK — ready for physics!", 20, 40);
        g.fillOval(100, 100, 40, 40);
    }
}