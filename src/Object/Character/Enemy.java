package Object.Character;

import Controller.GameState;
import Object.Item.Weapon;

import java.awt.*;

public class Enemy extends Character {
    private final int START_X = 9;
    private final int START_Y = 39;
    private final int IMAGE_WIDTH = 14;
    private final int IMAGE_HEIGHT = 14;
    private final int IMG_RESIZED_W = 50;
    private final int IMG_RESIZED_H = 50;

    private final int SPEED_Y = 0;
    private int moveCount = 0;
    private double shootFreq;

    //The initial random frequency the enemy will shoot.
    private double randFreq = (Math.random() * (31)) + (shootFreq-0.5);

    private int direction = 1; // facing right

    protected int health;

    protected Weapon weapon;

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y, GameState level, Weapon weapon, int shootFreq, int health) {
        super(w,h,x,y);

        this.setImage(level);
        this.loadImage(START_X, START_Y, IMAGE_WIDTH, IMAGE_HEIGHT);

        this.setSpeedY(SPEED_Y);
        this.weapon = weapon;
        this.shootFreq = shootFreq;
        this.health = health;
    }


    private void setImage(GameState level) {
        switch(level) {
            case TUTORIAL:
                imageLocation = "Assets/enemy_blue.png";
                break;
            case LEVEL_1:
                imageLocation = "Assets/enemy_red.png";
                break;
            case LEVEL_2:
                imageLocation = "Assets/enemy_green.png";
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int dir) {
        direction = dir;
    }

    public void move() {
        super.move();
        //Counter to keep track of when the enemy has to shoot again.
        moveCount++;
        weapon.moveShot();
        //Once the counter reaches the desired period, shoot the weapon again.
        if (moveCount > randFreq) {
            shoot();
            //Randomly generate the count for when next time the enemy will shoot.
            randFreq = (Math.random() * (31)) + (shootFreq - 0.5);
            moveCount = 0;
        }
    }

    public void shoot() { weapon.shoot(posX,posY,direction);}

    public int getPoints() { return points; }

    public int getHealth() { return health; }

    public Weapon getWeapon() { return weapon; }

    public int getDirection() { return direction;}

    public void setDirection(int dir) { direction = dir; }

    public void takeDamage(int damage){ health = health - damage; }

    /**
     * Paints enemy. Called by Level object.
     */
    @Override
    public void paintObject(Graphics2D g) {
        if (direction == 1) {
            g.drawImage(charImageRight, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        } else {
            g.drawImage(charImageLeft, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        }
      
        g.setColor(Color.GREEN);
        g.drawString(health+"", (int)(posX +(width/2)), (int)(posY+(height/2)));
        weapon.paintObject(g);
    }
}
