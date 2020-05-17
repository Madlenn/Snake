package com.company.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class Screen extends JPanel implements Runnable, KeyListener {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 200, HEIGHT = 200;
    private Thread thread;
    private boolean running = false;
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private Apple apple;
    private ArrayList<Apple> apples;
    private Random r;
    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    private int snakeSpeed = 250000 * 4;
    private boolean right = true, left = false, up = false, down = false;
    private int ticks = 0;

    public Screen() {

        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT + 20));

        setLayout(null);

        r = new Random();
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        start();
    }

    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }
        if (apples.size() == 0) {
            int xCoor = r.nextInt(19);
            int yCoor = r.nextInt(19);
            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }
        for (int i = 0; i < apples.size(); i++) {
            if (xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i++;
            }
        }
        for (int i = 0; i < snake.size(); i++) {
            if (xCoor == snake.get(i).getxCoor() &&
                    yCoor == snake.get(i).getyCoor()) {
                if (i != snake.size() - 1) {
                    stop();
                }
            }
        }
        if (xCoor < 0 || xCoor > 19 || yCoor < 0 || yCoor > 19) {
            stop();
        }


        ticks++;
        if (ticks > snakeSpeed) {
            if (right) xCoor++;
            if (left) xCoor--;
            if (up) yCoor--;
            if (down) yCoor++;
            ticks = 0;
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
            if (snake.size() > size) {
                snake.remove(0);
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT + 20);
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }

        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }

        String string = "Score " + (size - 5);
        g.setColor(Color.RED);
        g.drawString(string, 5, HEIGHT + 10);
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        setVisible(false);
        Menu.scores.add(size-5);
        final Frame[] frames = Frame.getFrames();
        for (final Frame frame : frames) {
            if (frame.isVisible() && frame.isActive()) {
                frame.dispose();
            }
        }
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            right = true;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        if (key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }


}
