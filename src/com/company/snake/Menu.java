package com.company.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 200, HEIGHT = 200;
    private JPanel contentPane;
    private JButton STARTButton;
    private JButton CLOSEButton;
    private JButton SCOREButton;
    private JButton BACKButton;
    public static List<Integer> scores = new ArrayList<>();

    public Menu() {
        setUndecorated(true);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GREEN);
        Menu component = this;
        STARTButton.setBounds(0, 0, 200, 70);
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame game = new JFrame();
                Screen screen = new Screen();
                game.add(screen);

                game.setUndecorated(true);
                game.setResizable(false);
                game.pack();
                game.setLocationRelativeTo(component);
                game.setVisible(true);
            }

        });

        CLOSEButton.setBounds(0, 70, 200,70);
        CLOSEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        SCOREButton.setBounds(0, 140, 200, 60);
        SCOREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame scoreFrame = new JFrame();
                JPanel scorePanel = new JPanel();

                JLabel score;
                if (scores.isEmpty()) {
                    score = new JLabel("Lista wyników jest pusta");
                } else {
                    StringBuilder builder = new StringBuilder("<html>twoje ostatnie wyniki to: <br/>");
                    scores.forEach(s -> builder.append(s).append("<br/> "));
                    builder.append("</html>");
                    score = new JLabel(builder.toString());

                }
                BACKButton = new JButton("BACK");
                BACKButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scoreFrame.dispose();
                    }
                });
                scorePanel.add(score);
                scoreFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
                scoreFrame.add(scorePanel);
                scoreFrame.setUndecorated(true);
                scoreFrame.setResizable(false);
                scoreFrame.pack();
                scoreFrame.setLocationRelativeTo(component);
                scoreFrame.setVisible(true);
                scorePanel.add(BACKButton);
            }
        });

        contentPane.add(SCOREButton);
        contentPane.add(STARTButton);
        contentPane.add(CLOSEButton);
        add(contentPane);
    }
}
