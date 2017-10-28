package com.guts.michael.views;

import com.guts.michael.Views;

import javax.swing.*;
import java.awt.*;

public class ClientView {

    private JFrame frame;
    private JPanel panel;

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 800;

    public ClientView() {
        panel = new JPanel();
        panel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.getDEFAULT_NAME());
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.isRESIZABLE());

        frame.add(panel);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void render(Graphics g) {
        g.drawLine(0, 0, 400, 400);
    }
}
