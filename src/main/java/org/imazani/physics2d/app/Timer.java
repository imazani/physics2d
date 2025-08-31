package org.imazani.physics2d.app;

public class Timer {

    private long prev = System.nanoTime();

    /** seconds since last call */
    public double tick() {
        long now = System.nanoTime();
        double dt = (now - prev) / 1_000_000_000.0;
        prev = now;
        return dt;
    }
}
