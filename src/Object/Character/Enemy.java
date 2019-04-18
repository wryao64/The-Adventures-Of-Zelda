package Object.Character;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy extends Character {

    public Enemy(int w, int h, int x, int y){
        super(w,h,x,y);
    }

    @Override
    public void paintObject(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
    }

    private int health;

    public void attack() { }

    public int getHealth() { return health; }

    public void takeDamage(int damage){
        health = health-damage;
        if(health <= 0){
            //die
        }
    }
}
