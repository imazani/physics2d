package org.imazani.physics2d.app;

import org.imazani.physics2d.render.AwtCanvas;

import javax.swing.*;
import java.awt.*;

public class SandboxWindow extends JFrame {

    private final AwtCanvas canvas;

    public SandboxWindow(String title, int width, int height) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.canvas = new AwtCanvas(width,height);
        add(canvas, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        // Canvas is now displayable, build the buffer strategy
        canvas.ensureBufferStrategy(3);
    }

    public AwtCanvas canvas() {
        return canvas;
    }


}
