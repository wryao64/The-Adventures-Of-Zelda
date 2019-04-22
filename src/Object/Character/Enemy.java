package Object.Character;

import Controller.GameState;
import Item.Weapon;

import java.awt.*;

public class Enemy extends Character {
    private final int START_X = 9;
    private final int START_Y = 39;
    private final int IMAGE_WIDTH = 14;
    private final int IMAGE_HEIGHT = 14;
    private final int IMG_RESIZED_W = 50;
    private final int IMG_RESIZED_H = 50;

    private final int SPEED_Y = 0;

    private int direction = 1; // facing right

    protected int health;
    protected int damage;
    protected Weapon weapon;

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y, GameState level) {
        super(w,h,x,y);

        this.setImage(level);
        this.loadImage(START_X, START_Y, IMAGE_WIDTH, IMAGE_HEIGHT);

        this.setSpeedY(SPEED_Y);
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

    /**
     * Paints enemy.
     * Called by Level object.
     */
    @Override
    public void paintObject(Graphics2D g) {
        if (direction == 1) {
            g.drawImage(charImageRight, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        } else {
            g.drawImage(charImageLeft, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        }
    }
  
    public int getPoints() { return points; }

    public void attack() { }

    public int getHealth() { return health; }

    public void takeDamage(int damage){
        health = health - damage;
    }
}
