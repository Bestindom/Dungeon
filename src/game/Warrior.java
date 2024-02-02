package game;

import javax.swing.*;

public class Warrior extends Character {

    public Warrior(int id, String name, int gold, int objects, int hp, int speed, int potion, int x, int y, JLabel image) {
        super(id, name, gold, objects, hp, speed, potion, x, y,image);
    }

    @Override
    public String toString() {
        return "Warrior{" +
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
