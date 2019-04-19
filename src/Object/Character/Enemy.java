package Object.Character;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy extends Character {
    private final int SPEED_Y = 0;

    private int health;

    public Enemy(int w, int h, int x, int y){
        super(w,h,x,y);
        this.setSpeedY(SPEED_Y);
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

    public void attack() { }

    public int getHealth() { return health; }

    public void takeDamage(int damage) {
        health = health-damage;
        if(health <= 0){
            //die
        }
    }
}
