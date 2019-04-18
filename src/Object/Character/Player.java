package Object.Character;

import Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Character {

    private final int jumpHeight = 17;
    private final double movementSpeed = 3.5;

    private double startPosX;
    private double startPosY;

    private Weapon weapon;
    private int lives;
    boolean canJump;

    public Player(double w, double h, double x, double y){
        super(w,h,x,y);
        startPosY = x;
        startPosX = y;
    }

    public void jump() {
        if(canJump) {
            speedY = - jumpHeight;
        }else{
            speedY = 0;
        }
    }

    /**
     * Simulates effect of gravity by accelerating player speed in y direction.
     */
    public void fall(int gravity, int maxSpeedY){
        if(speedY <  maxSpeedY) {
            speedY += gravity;
        }else{
            speedY = maxSpeedY;
        }
    }

    /*
    public void shootWeapon(int speedDir){
        weapon.shoot(speedDir);
    }*/

    /*
    Resets the player back to the starting position in the level ie when they die
     */
    public void initPosition(){
        posX = startPosX;
        posY = startPosY;
    }

    public int getLives(){
        return lives;
    }

    public void loseLife(){
        lives--;
    }

    public double getMovementSpeed() { return movementSpeed; }

    public void setCanJump(boolean jump) {canJump = jump;}

    /**
     * Painting the player. Called by the level class
     */
    public void paintObject(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
    }

}
