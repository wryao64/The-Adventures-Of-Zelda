package Object.Character;

import Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy extends Character {
    private final int SPEED_Y = 0;

    private int direction = 1; // facing right

    protected int health;
    protected int damage;
    protected Weapon weapon;

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y){
        super(w,h,x,y);
        this.setSpeedY(SPEED_Y);
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
        g.setColor(Color.BLUE);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
    }
  
    public int getPoints() { return points; }

    public void attack() { }

    public int getHealth() { return health; }

    public void takeDamage(int damage){
        health = health - damage;
    }
}
