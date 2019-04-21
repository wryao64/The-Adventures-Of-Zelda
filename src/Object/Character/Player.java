package Object.Character;

import Object.Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Player extends Character {

    private final int jumpHeight = 17;
    private final double movementSpeed = 3.5;

    private double startPosX;
    private double startPosY;

    //The direction the player is facing. Initialized as 1 (facing to the right).
    private double playerDir = 1;

    private Weapon weapon;
    private int lives = 5;
    boolean canJump;

    private int enemiesKilled = 0;
    private int bossesKilled = 0;
    private int orbsCollected = 0;

    public Player(double w, double h, double x, double y){
        super(w,h,x,y);
        startPosY = y;
        startPosX = x;
        weapon = new Weapon(25,250,7);
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
        //System.out.println("playerPosX" + posX + " playerPosY " + posY);
        weapon.shoot(posX,posY,playerDir);
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

    /**
     * Getters and Setters.
     */
    public int getLives(){ return lives; }

    public int getEnemiesKilled(){ return enemiesKilled; }

    public void loseLife(){ lives--; }

    public void addToEnemiesKilled(int points) { enemiesKilled ++; }

    public double getMovementSpeed() { return movementSpeed; }

    public void setCanJump(boolean jump) { canJump = jump; }

    public void setPlayerDir(int direction) { playerDir = direction; }

    public Weapon getWeapon() { return weapon; }


    /**
     * Returns all the stats necessary to calculated the final score.
     */
    public HashMap<String,Integer> getFinalStats() {

        HashMap<String,Integer> scoreMap = new HashMap<>();
        scoreMap.put("Enemies Slain: ", enemiesKilled);
        scoreMap.put("Bosses Slain: ", bossesKilled);
        scoreMap.put("Orbs Collected: ", orbsCollected);
        scoreMap.put("Lives left: ", lives);
        return scoreMap;
    }

    /**
     * Moves the player as well as any bullets the player has fired in play.
     */
    public void move(){
        super.move();
        weapon.moveShot();
    }

    /**
     * Painting the player. Called by the level class
     */
    public void paintObject(Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
        weapon.paintObject(g);
}

}
