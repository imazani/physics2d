package org.imazani.physics2d.app;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SandboxDemo implements FixedTimestepLoop.Simulation{
    // simple "particle"
    private double x = 120, y = 120; // pixels
    private double vx = 140, vy = 90; // pixels/second
    private final double r = 12;    // radius (px)

    @Override
    public void update(double dt, int w, int h) {
        // move
        x += vx * dt;
        y += vy * dt;

        // Bounce on windows
        if (x < r)          { x = r;       vx =  Math.abs(vx); }
        if (x > w - r)      { x = w - r;   vx = -Math.abs(vx); }
        if (y < r)          { y = r;       vy =  Math.abs(vy); }
        if (y > h - r)      { y = h - r;   vy = -Math.abs(vy); }
    }

    @Override
    public void render(Graphics2D g, double alpha, int w, int h) {
        // Clear
        g.setColor(Color.BLACK);
        g.fillRect(0,0, w, h);

        // Interpolated position (not strictly necessary for v0.1, but shows the idea)
        double ix = x + vx * alpha * (1.0 / 60.0);
        double iy = y + vy * alpha * (1.0 / 60.0);

        // Draw ball
        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(ix - r, iy - r, r * 2, r * 2));

        // HUD
        g.setColor(Color.GRAY);
        g.drawString("v0.1 Fixed step 60 Hz Interp α=" + String.format("%.2f", alpha), 10, 20);
    }
}
