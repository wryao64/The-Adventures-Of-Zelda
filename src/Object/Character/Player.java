package Object.Character;

import Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends Character {

    private final int jumpHeight = 17;
    private final double movementSpeed = 3.5;

    private double startPosX;
    private double startPosY;

    //The direction the player is facing. Initialized as 1 (facing to the right).
    private double playerDir = 1;

    private Weapon weapon;
    private int lives;
    boolean canJump;

    public Player(double w, double h, double x, double y){
        super(w,h,x,y);
        startPosY = x;
        startPosX = y;
        weapon = new Weapon(10,250,7);
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


    /**
     * Player shoots the weapon it holds.
     */
    public void shootWeapon(){
        weapon.shoot(playerDir,posX,posY);
    }

    public int giveDamage(){
        return weapon.getAttackDamage();
    }
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

    public void setCanJump(boolean jump) { canJump = jump; }

    public void setPlayerDir(int direction) { playerDir = direction; }

    /**
     * Moves the player as well as any bullets the player has fired in play.
     */
    public void move(){
        super.move();
        weapon.moveShot();
    }

   public Weapon getWeapon() {
         return weapon;
   }

    /**
     * Painting the player. Called by the level class
     */
    public void paintObject(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
        weapon.paint(g);
    }

}
