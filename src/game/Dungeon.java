package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dungeon {

    //orden de lo que haré

    //crear el panel

    //clase abstracta de personaje + warrior, priest, wizard, skeleton :)


    private JPanel panelMain;
    private JPanel panelTitle;
    private JPanel panelCenter;
    private JLabel labelSkeleton1;
    private JLabel labelSkeleton2;
    private JLabel labelSkeleton3;
    private JLabel labelSkeleton4;
    private JLabel labelHp;
    private JLabel labelGold;
    private JLabel labelObject;
    private JLabel labelScore;
    private JLabel labelBonus;
    private JLabel labelCharacter;
    private int gold;
    private int lives;
    private int bonus;
    private int tileWidth = 32;
    private int tileHeight = 32;
    private int screenWidth = 1100;
    private int screenHeight = 600;
    private int numCols = (int) Math.ceil(screenWidth / (double) tileWidth);
    private int numRows = (int) Math.ceil(screenHeight / (double) tileHeight);
    private int characterType;
    Warrior w1;
    Priest p1;
    Mage m1;


    String spriteWarriorUp = "src/images/warrior/warrior_up.gif";
    String spriteWarriorDown = "src/images/warrior/warrior_down.gif";
    String spriteWarriorRight = "src/images/warrior/warrior_right.gif";
    String spriteWarriorLeft = "src/images/warrior/warrior_left.gif";

    String spriteMageUp = "src/images/wizard/wizard_up.gif";
    String spriteMageDown = "src/images/wizard/wizard_down.gif";
    String spriteMageRight = "src/images/wizard/wizard_right.gif";
    String spriteMageLeft = "src/images/wizard/wizard_left.gif";

    String spritePriestUp = "src/images/priest/priest_up.gif";
    String spritePriestDown = "src/images/priest/priest_down.gif";
    String spritePriestRight = "src/images/priest/priest_right.gif";
    String spritePriestLeft = "src/images/priest/priest_left.gif";

    String spriteSkeletonUp = "src/images/skeleton/skeleton_up.gif";
    String spriteSkeletonDown = "src/images/skeleton/skeleton_down.gif";
    String spriteSkeletonRight = "src/images/skeleton/skeleton_right.gif";
    String spriteSkeletonLeft = "src/images/skeleton/skeleton_left.gif";

    //Direction 0 = UP  1 = DOWN   2 = Right    3 = Left
    Skeleton Skeleton1 = new Skeleton ("S1", 0,0,labelSkeleton1, 1);
    Skeleton Skeleton2 = new Skeleton ("S2", 0,0,labelSkeleton2, 2);
    Skeleton Skeleton3 = new Skeleton ("S3", 0,0,labelSkeleton3, 1);
    Skeleton Skeleton4 = new Skeleton ("S4", 0,0,labelSkeleton4, 2);

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Dungeon");
        frame.setContentPane(new Dungeon().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Obtener el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular las coordenadas x e y para centrar la ventana
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;

        // Establecer las coordenadas para centrar la ventana
        frame.setLocation(x, y);
        frame.setLayout(null);

        //Cambia el icono de la ventana
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icono = pantalla.getImage("src/images/politecnics.png");
        frame.setIconImage(icono);
    }

    public Dungeon() {
        panelMain.setPreferredSize(new Dimension(1100,600));
        //Esta línea es para que ALL funcione bien :)
        panelMain.setSize(new Dimension(1100,600));
        //Is important to agg this line too in below method
        panelMain.setLayout(null);
        //Esencial para que los putos KeyEvent reaccionen!!!!!!!!!!!! below line :)
        panelMain.setFocusable(true);

        showPanelTitle();
        showPanelCenter();

        Timer timer = new Timer(1000, new TimerActionListener());
        timer.start();


    }

    private void showPanelTitle(){
        panelTitle = new JPanel();
        panelTitle.setLocation(0,0);
        panelTitle.setSize(panelMain.getWidth(),50);
        panelTitle.setBackground(Color.GRAY);

        panelMain.add(panelTitle);

        labelScore = new JLabel();
        labelScore.setText("0 gold");

        //Marronero set lives ;)
        labelHp = new JLabel();
        labelHp.setText("? lives");

        labelBonus = new JLabel();
        labelBonus.setText("0 Bonus");

        panelTitle.add(labelScore);
        panelTitle.add(labelHp);
        panelTitle.add(labelBonus);

    }

    private void showPanelCenter() {
        panelCenter = new JPanel();
        panelCenter.setLayout(null);
        panelCenter.setLocation(0,panelTitle.getHeight());
        panelCenter.setSize(panelMain.getWidth(),panelMain.getHeight() - panelTitle.getHeight());

        panelMain.add(panelCenter);

        int x = 0;
        int y = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JLabel background = new JLabel();
                background.setSize(tileWidth, tileHeight);
                ImageIcon imageicon = new ImageIcon("src/images/dungeon/tile001.png");
                Icon icon2 = new ImageIcon(imageicon.getImage().getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_DEFAULT));
                background.setIcon(icon2);
                background.setLocation(x, y); // establecer la ubicación
                panelCenter.add(background);
                x += tileWidth; // mover la posición x
            }
            x = 0; // restablecer la posición x
            y += tileHeight; // mover la posición y
        }



        characterType = chooseCharacter();
        showCharacter();
        panelMain.addKeyListener(new PanelMainListener());
        showSkeleton();
        showSkeleton2();
        showgold();
        showObject();

    }

    private void showCharacter(){

        labelCharacter = new JLabel();
        labelCharacter.setSize(64,64);

        switch (characterType){
            case 0:
                ImageIcon imageIcon = new ImageIcon(spriteWarriorRight);
                Icon icon = new ImageIcon(
                        imageIcon.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                );
                labelCharacter.setIcon(icon);
                break;

            case 1:
                ImageIcon imageIcon2 = new ImageIcon(spritePriestRight);
                Icon icon2 = new ImageIcon(
                        imageIcon2.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                );
                labelCharacter.setIcon(icon2);
                break;

            case 2:

                ImageIcon imageIcon3 = new ImageIcon(spriteMageRight);
                Icon icon3 = new ImageIcon(
                        imageIcon3.getImage().getScaledInstance(labelCharacter.getWidth(), labelCharacter.getHeight(), Image.SCALE_DEFAULT)
                );
                labelCharacter.setIcon(icon3);
                break;
        }

        labelCharacter.setLocation(100, 100);
        panelCenter.add(labelCharacter, 0);
    }

    public int chooseCharacter(){
        String[] optionCharacter = {"Warrior", "Priest", "Mage"};
        int option = 0;
        option = JOptionPane.showOptionDialog(null, "Choose Character", "Character Selection", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, optionCharacter, 0);

        if (option == 0){
            w1 = new Warrior(01, "Ivan", 5, 45, 5, 3, 0, 0, 0, labelCharacter);
            w1.toString();
            characterType = 0;
            setLives(w1.getHp());
        } else if (option == 1) {
            p1 = new Priest(01, "Pedro", 5, 45, 4,5,0, 0, 0,labelCharacter);
            p1.toString();
            characterType = 1;
            setLives(p1.getHp());
        } else if (option == 2) {
            m1 = new Mage(01, "Kolo", 5, 45, 3, 7, 0, 0, 0, labelCharacter);
            m1.toString();
            characterType = 2;
            setLives(m1.getHp());
        }
        return characterType;
    }

    private class PanelMainListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelCharacter.getX();
            int y = labelCharacter.getY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> x += 5;
                case KeyEvent.VK_LEFT -> x -= 5;
                case KeyEvent.VK_UP -> y -= 5;
                case KeyEvent.VK_DOWN -> y += 5;
            }

            //Delimita el movimiento de mi character al size de mi panelCenter :)
            if (  (x >= 0 && x <= panelCenter.getWidth() - labelCharacter.getWidth()) &&  (y >= 0 && y <= panelCenter.getHeight() - labelCharacter.getHeight())  )  {
                labelCharacter.setLocation(x,y);
            }
        }
    }

    public void showSkeleton() {
        labelSkeleton1 = new JLabel();
        panelCenter.setLayout(null);
        labelSkeleton1.setSize(64,64);
        ImageIcon imageIcon = new ImageIcon(spriteSkeletonRight);
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelSkeleton1.getWidth(), labelSkeleton1.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSkeleton1.setIcon(icon);
        labelSkeleton1.setLocation(0,0);

        panelCenter.add(labelSkeleton1,0);
    }

    public void showSkeleton2() {
        labelSkeleton2 = new JLabel();
        panelCenter.setLayout(null);
        labelSkeleton2.setSize(64,64);
        ImageIcon imageIcon = new ImageIcon(spriteSkeletonRight);
        Icon icon = new ImageIcon(
                imageIcon.getImage().getScaledInstance(labelSkeleton2.getWidth(), labelSkeleton2.getHeight(), Image.SCALE_DEFAULT)
        );
        labelSkeleton2.setIcon(icon);
        labelSkeleton2.setLocation(0,0);

        panelCenter.add(labelSkeleton2,0);
    }

    public void moveSkeleton(){
        int ySkeleton = labelSkeleton1.getY();
        int ySkeleton2 = labelSkeleton2.getY();
        int xSkeleton = labelSkeleton1.getX();
        int xSkeleton2 = labelSkeleton2.getX();
        if (Skeleton1.getDirection() == 1){
            ySkeleton += 60;
            //Setteo la posición del esqueleto
            labelSkeleton1.setLocation(xSkeleton, ySkeleton);
            Skeleton1.setX(xSkeleton);
            Skeleton1.setY(ySkeleton);
        } else if (Skeleton1.getDirection() == 0) {
            ySkeleton -= 60;
            labelSkeleton1.setLocation(xSkeleton, ySkeleton);
            Skeleton1.setX(xSkeleton);
            Skeleton1.setY(ySkeleton);
        }
        //Aquí choca con el panel SUPERIOR ---> ESQUELETO PA ABAJO
        if (ySkeleton < labelSkeleton1.getHeight()){
            Skeleton1.setDirection(1);
            ImageIcon imageIcon = new ImageIcon(spriteSkeletonDown);
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(labelSkeleton1.getWidth(), labelSkeleton1.getHeight(), Image.SCALE_DEFAULT)
            );
            labelSkeleton1.setIcon(icon);
        //Aquí choca con el panel INFERIOR ---> ESQUELETO PA ARRIBA
        } else if (ySkeleton > panelCenter.getHeight() - labelSkeleton1.getHeight()) {
            Skeleton1.setDirection(0);
            ImageIcon imageIcon = new ImageIcon(spriteSkeletonUp);
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(labelSkeleton1.getWidth(), labelSkeleton1.getHeight(), Image.SCALE_DEFAULT)
            );
            labelSkeleton1.setIcon(icon);
        }
        //Right
        if (Skeleton2.getDirection() == 2){
            xSkeleton2 += 60;
            labelSkeleton2.setLocation(xSkeleton2, ySkeleton2);
            Skeleton2.setX(xSkeleton2);
            Skeleton2.setY(ySkeleton2);
        //Left
        } else if (Skeleton2.getDirection() == 3) {
            xSkeleton2 -= 60;
            labelSkeleton2.setLocation(xSkeleton2, ySkeleton2);
            Skeleton2.setX(xSkeleton2);
            Skeleton2.setY(ySkeleton2);
        }
        //si choca va para la izquieda VA PARA LA DERECHA
        if (xSkeleton2 < labelSkeleton2.getWidth()){
            Skeleton2.setDirection(2);
            ImageIcon imageIcon = new ImageIcon(spriteSkeletonRight);
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(labelSkeleton2.getWidth(), labelSkeleton2.getHeight(), Image.SCALE_DEFAULT)
            );
            labelSkeleton2.setIcon(icon);
        } else if (xSkeleton2 > panelCenter.getWidth() - labelSkeleton2.getWidth()) {
            Skeleton2.setDirection(3);
            ImageIcon imageIcon = new ImageIcon(spriteSkeletonLeft);
            Icon icon = new ImageIcon(
                    imageIcon.getImage().getScaledInstance(labelSkeleton2.getWidth(), labelSkeleton2.getHeight(), Image.SCALE_DEFAULT)
            );
            labelSkeleton2.setIcon(icon);
        }
    }

    private void showgold() {
        labelGold = new JLabel();
        labelGold.setSize(32, 32);
        ImageIcon imageicon = new ImageIcon("src/images/dungeon/dollar.png");
        Icon icon = new ImageIcon(imageicon.getImage().getScaledInstance(labelGold.getWidth(), labelGold.getHeight(), Image.SCALE_DEFAULT));

        labelGold.setIcon(icon);

        int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelGold.getWidth()));
        int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelGold.getHeight()));
        labelGold.setLocation(randomIntX, randomIntY);
        panelCenter.add(labelGold, 0);
        System.out.println("X " + labelGold.getX() + "  Y" + labelGold.getY());

    }

    private void showObject() {
        labelObject = new JLabel();
        labelObject.setSize(32, 32);
        if (characterType == 0){

            ImageIcon imageicon = new ImageIcon("src/images/dungeon/sword.png");
            Icon icon = new ImageIcon(imageicon.getImage().getScaledInstance(labelObject.getWidth(), labelObject.getHeight(), Image.SCALE_DEFAULT));

            labelObject.setIcon(icon);

            int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
            int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
            labelObject.setLocation(randomIntX, randomIntY);
            panelCenter.add(labelObject, 0);
            System.out.println("X " + labelObject.getX() + "  Y" + labelObject.getY());

        } else if (characterType == 1) {

            ImageIcon imageicon = new ImageIcon("src/images/dungeon/mitra.png");
            Icon icon = new ImageIcon(imageicon.getImage().getScaledInstance(labelObject.getWidth(), labelObject.getHeight(), Image.SCALE_DEFAULT));

            labelObject.setIcon(icon);

            int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
            int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
            labelObject.setLocation(randomIntX, randomIntY);
            panelCenter.add(labelObject, 0);
            System.out.println("X " + labelObject.getX() + "  Y" + labelObject.getY());

        } else if (characterType == 2) {

            ImageIcon imageicon = new ImageIcon("src/images/dungeon/potion.png");
            Icon icon = new ImageIcon(imageicon.getImage().getScaledInstance(labelObject.getWidth(), labelObject.getHeight(), Image.SCALE_DEFAULT));

            labelObject.setIcon(icon);

            int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
            int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
            labelObject.setLocation(randomIntX, randomIntY);
            panelCenter.add(labelObject, 0);
            System.out.println("X " + labelObject.getX() + "  Y" + labelObject.getY());
        }
    }

    private void collectGold(JLabel character) {
        Rectangle rchar = new Rectangle(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        Rectangle rgold = new Rectangle(labelGold.getX(), labelGold.getY(), labelGold.getWidth(), labelGold.getHeight());
        if (rchar.intersects(rgold)) {
            if (characterType == 0){
                w1.setGold(w1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelGold.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelGold.getHeight()));
                labelGold.setLocation(randomIntX, randomIntY);
                gold++;
                labelScore.setText(gold + " gold");
                if (gold >= 10) {
                    System.exit(0);
                }
            } else if (characterType == 1) {
                p1.setGold(p1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelGold.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelGold.getHeight()));
                labelGold.setLocation(randomIntX, randomIntY);
                gold++;
                labelScore.setText(gold + " gold");
                if (gold >= 10) {
                    System.exit(0);
                }
            } else if (characterType == 2) {
                m1.setGold(m1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelGold.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelGold.getHeight()));
                labelGold.setLocation(randomIntX, randomIntY);
                gold++;
                labelScore.setText(gold + " gold");
                if (gold >= 10) {
                    System.exit(0);
                }
            }
        }
    }

    private void collectObject(JLabel character) {
        Rectangle rchar = new Rectangle(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        Rectangle robject = new Rectangle(labelObject.getX(), labelObject.getY(), labelObject.getWidth(), labelObject.getHeight());
        if (rchar.intersects(robject)) {
            if (characterType == 0){
                w1.setGold(w1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
                labelObject.setLocation(randomIntX, randomIntY);
                gold += 2;
                bonus++;
                labelScore.setText(gold + " gold");
                labelBonus.setText(bonus + " bonus");
                if (gold >= 10) {
                    System.exit(0);
                }
            } else if (characterType == 1) {
                p1.setGold(p1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
                labelObject.setLocation(randomIntX, randomIntY);
                gold += 2;
                bonus++;
                labelScore.setText(gold + " gold");
                labelBonus.setText(bonus + " bonus");
                if (gold >= 10) {
                    System.exit(0);
                }
            } else if (characterType == 2) {
                m1.setGold(m1.getGold() + 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelObject.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelObject.getHeight()));
                labelObject.setLocation(randomIntX, randomIntY);
                gold += 2;
                bonus++;
                labelScore.setText(gold + " gold");
                labelBonus.setText(bonus + " bonus");
                if (gold >= 10) {
                    System.exit(0);
                }
            }
        }
    }
    public void intersectionSkeleton (JLabel character){
        Rectangle rchar = new Rectangle(character.getX(), character.getY(), character.getWidth(), character.getHeight());
        Rectangle rskely1 = new Rectangle(labelSkeleton1.getX(), labelSkeleton1.getY(), labelSkeleton1.getWidth(), labelSkeleton1.getHeight());
        Rectangle rskely2 = new Rectangle(labelSkeleton2.getX(), labelSkeleton2.getY(), labelSkeleton2.getWidth(), labelSkeleton2.getHeight());

        if (rchar.intersects(rskely1)) {
            if (characterType == 0){
                w1.setHp(w1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton1.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton1.getHeight()));
                labelSkeleton1.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            } else if (characterType == 1) {
                p1.setHp(p1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton1.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton1.getHeight()));
                labelSkeleton1.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            } else if (characterType == 2) {
                m1.setHp(m1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton1.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton1.getHeight()));
                labelSkeleton1.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            }
        }

        if (rchar.intersects(rskely2)) {
            if (characterType == 0){
                w1.setHp(w1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton2.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton2.getHeight()));
                labelSkeleton2.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            } else if (characterType == 1) {
                p1.setHp(p1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton2.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton2.getHeight()));
                labelSkeleton2.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            } else if (characterType == 2) {
                m1.setHp(m1.getHp() - 1);
                int randomIntX = (int) (Math.random() * (panelCenter.getWidth() - labelSkeleton2.getWidth()));
                int randomIntY = (int) (Math.random() * (panelCenter.getHeight() - labelSkeleton2.getHeight()));
                labelSkeleton2.setLocation(randomIntX, randomIntY);
                setLives(getLives() - 1);
                labelHp.setText(lives + " lives");
                if (lives == 0) {
                    System.exit(0);
                }
            }
        }
    }


    public class TimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            moveSkeleton();
            collectGold(labelCharacter);
            collectObject(labelCharacter);
            intersectionSkeleton(labelCharacter);
        }
    }

}
