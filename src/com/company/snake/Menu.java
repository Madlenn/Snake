package com.company.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final int WIDTH = 200, HEIGHT = 200;
    private JButton STARTButton;
    private JButton CLOSEButton;
    private JLabel score;

    public Menu() {
        setUndecorated(true);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        contentPane = new JPanel();
        /*contentPane.setLayout(new GridLayout(0, 1, 0, 0));*/
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GREEN);
        Menu component = this;
        STARTButton.setBounds(0, 0, 200, 100);
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame game = new JFrame();
                Screen screen = new Screen();
                game.add(screen);
                JPanel scorePanel = new JPanel();
                scorePanel.add(score);
                game.setUndecorated(true);
                game.setResizable(false);
                game.pack();
                game.setLocationRelativeTo(component);
                game.setVisible(true);
            }

        });

        CLOSEButton.setBounds(0, 100, 200, 100);
        CLOSEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(STARTButton);

        contentPane.add(CLOSEButton);
        score = new JLabel("twoj wynik ");
        score.setBounds(0, 200, 200, 30);
        add(contentPane);
    }
}
