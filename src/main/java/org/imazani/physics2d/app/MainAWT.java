package org.imazani.physics2d.app;

import org.imazani.physics2d.render.AwtCanvas;

public class MainAWT {

    public static void main(String[] args) {

        // Build the window + canvas
        var window = new SandboxWindow("Physics2D v0.1 (AWT)", 800, 600);
        AwtCanvas canvas = window.canvas();

        // Wire the loop with a simple demo simulation
        double targetHz = 60.0;
        var sim = new SandboxDemo();
        var loop = new FixedTimestepLoop(canvas, sim, targetHz);

        // Stop loop when window closes
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) {
                loop.stop();
            }
        });

        loop.start();
    }
}
