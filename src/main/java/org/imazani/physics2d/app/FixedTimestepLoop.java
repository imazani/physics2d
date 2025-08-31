package org.imazani.physics2d.app;

import org.imazani.physics2d.render.AwtCanvas;

import java.awt.Graphics2D;

public class FixedTimestepLoop implements Runnable {

    public interface Simulation {
        // Advance simulation by fixed dt`
        void update(double dt, int canvasWidth, int canvasHeight);

        // draw a frame; alpha in [0,1) is interpolation fraction
        void render(Graphics2D g, double alpha, int canvasWidth, int canvasHeight);

    }

    private final AwtCanvas canvas;
    private final Simulation sim;
    private final double step; // seconds per physics step
    private volatile boolean running;

    public FixedTimestepLoop(AwtCanvas canvas, Simulation sim, double targetHz) {
        this.canvas = canvas;
        this.sim = sim;
        this.step = 1.0 / targetHz;
    }

    public void start() {
        if (running) return;
        running = true;
        Thread t = new Thread(this, "FixedTimeStepLoop");
        t.setDaemon(true);
        t.start();
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        double accumulator = 0.0;
        final double maxFrame = 0.25; // cap huge pauses to avoid spiral-of-death

        while (running) {
            double dt = timer.tick();
            if(dt > maxFrame) dt = maxFrame;
            accumulator += dt;

            // Update in fixed-size steps for determinism
            while (accumulator >= step){
                sim.update(step, canvas.getWidth(), canvas.getHeight());
                accumulator -= step;
            }

            // Interpolation factor for rendering between steps
            double alpha = accumulator / step;

            // Draw a frame
            Graphics2D g = canvas.beginFrame();
            if (g != null) {
                sim.render(g, alpha, canvas.getWidth(), canvas.getHeight());
                canvas.endFrame(g);
            }

            // Yield a bit so we don't burn 100% CPU
            try {Thread.sleep(1); } catch (InterruptedException ignored){}
        }
    }
}
