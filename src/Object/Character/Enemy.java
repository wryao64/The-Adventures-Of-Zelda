package Object.Character;

import Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy extends Character {
    private final int SPEED_Y = 0;
    private int moveCount = 0;
    private double shootFreq;
    private double randFreq;

    private int direction = 1; // facing right

    protected int health;
    protected Weapon weapon;

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y, Weapon weapon,int shootFreq, int health){
        super(w,h,x,y);
        this.setSpeedY(SPEED_Y);
        this.weapon = weapon;
        this.shootFreq = shootFreq;
        this.health = health;

        //Random frequency the enemy will shoot.
        randFreq = (Math.random() * (31)) + (shootFreq-0.5);
    }

    public void move() {
        //Counter to keep track of when the enemy has to shoot again.
        moveCount++;
        super.move();
        weapon.moveShot();

        //Once the counter reaches the desired period, shoot the weapon again.
        if(moveCount > randFreq) {
            shoot();
            //Randomly generate the count for when next time the enemy will shoot.
            randFreq = (Math.random() * (31)) + (shootFreq-0.5);
            moveCount = 0;
        }
    }

    public void shoot() { weapon.shoot(direction,posX,posY);}

    public int getPoints() { return points; }

    public int getHealth() { return health; }

    public Weapon getWeapon() { return weapon; }

    public int getDirection() { return direction;}

    public void setDirection(int dir) { direction = dir; }

    public void takeDamage(int damage){
        health = health - damage;
    }

    /**
     * Paints enemy.
     * Called by Level object.
     */
    @Override
    public void paintObject(Graphics2D g) {

        g.setColor(Color.BLUE);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
        weapon.paint(g);
    }
}
