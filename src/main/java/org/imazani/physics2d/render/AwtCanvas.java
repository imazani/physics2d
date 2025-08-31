package org.imazani.physics2d.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;

public class AwtCanvas extends Canvas {
    public AwtCanvas(int width, int height) {
        setSize(width, height);
        setBackground(Color.BLACK);
        setIgnoreRepaint(true); // We want to control exactly when we paint
    }

    public void ensureBufferStrategy(int buffers) {

        if (getBufferStrategy() == null) {
            createBufferStrategy(buffers);
        }
    }

    public Graphics2D beginFrame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) return null;
        return (Graphics2D) bs.getDrawGraphics();
    }

    public void endFrame(Graphics2D g) {
        if(g != null) g.dispose();
        BufferStrategy bs = getBufferStrategy();
        if (bs != null) {
            bs.show(); // swap buffer
            Toolkit.getDefaultToolkit().sync(); // Does this reduce tearing? How? Should google this!!!
        }
    }
}