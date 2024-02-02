package game;

import javax.swing.*;

public abstract class Character {

    protected int id;
    protected String name;
    protected int gold;
    protected int objects;
    protected int hp;
    protected int speed;
    protected int potion;
    protected int x;
    protected int y;
    protected JLabel image;

    public Character(int id, String name, int gold, int objects, int hp, int speed, int potion, int x, int y, JLabel image) {
        this.id = id;
        this.name = name;
        this.gold = gold;
        this.objects = objects;
        this.hp = hp;
        this.speed = speed;
        this.potion = potion;
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getObjects() {
        return objects;
    }

    public void setObjects(int objects) {
        this.objects = objects;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
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

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gold=" + gold +
                ", objects=" + objects +
                ", hp=" + hp +
                ", speed=" + speed +
                ", potion=" + potion +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
