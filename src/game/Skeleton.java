package game;

import javax.swing.*;

public class Skeleton {

    private String name;
    private int x;
    private int y;
    private JLabel image;
    private int direction;

    public Skeleton(String name, int x, int y, JLabel image, int direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.image = image;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JLabel getImage() {
        return image;
    }

    public void setImage(JLabel image) {
        this.image = image;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
