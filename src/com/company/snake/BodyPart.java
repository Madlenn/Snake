package com.company.snake;

import java.awt.*;

public class BodyPart {
    private int xCoor, yCoor, width, height;

    public BodyPart(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(xCoor * width, yCoor * height, width, height);
        g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 4, height - 4);

    }

    public int getxCoor() {
        return xCoor;
    }


    public int getyCoor() {
        return yCoor;
    }

}
