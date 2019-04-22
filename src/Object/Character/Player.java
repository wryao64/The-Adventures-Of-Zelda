package Object.Character;

import Object.Item.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Player extends Character {
    private final int START_X = 4;
    private final int START_Y = 4;
    private final int IMAGE_WIDTH = 20;
    private final int IMAGE_HEIGHT = 28;
    private final int IMG_RESIZED_W = 40;
    private final int IMG_RESIZED_H = 50;
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

    BufferedImage charImageRight1;
    BufferedImage charImageRight2;
    BufferedImage charImageLeft1;
    BufferedImage charImageLeft2;

    public Player(double w, double h, double x, double y){
        super(w,h,x,y);

        imageLocation = "Assets/player.png";
        this.loadImage(START_X, START_Y, IMAGE_WIDTH, IMAGE_HEIGHT);

        startPosY = y;
        startPosX = x;
        weapon = new Weapon(25,250,7);

    }

    public void jump() {
        if(canJump) {
            System.out.println("Jumped");
            speedY = - jumpHeight;
            canJump = false;
        }else{
            System.out.println("Couldn't Jump");
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

    public Rectangle2D getBounds() {
        Rectangle2D rectangle;
        if(playerDir == 1) {
            rectangle = new Rectangle2D.Double(posX + 15,posY,width-10,height);
        } else {
            rectangle = new Rectangle2D.Double(posX,posY,width-15,height);
        }
        return rectangle;
    }
    /**
     * Painting the player. Called by the level class
     */
    public void paintObject(Graphics2D g) {
        if (playerDir == 1) {
            g.drawImage(charImageRight, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        } else {
            g.drawImage(charImageLeft, (int) posX, (int) posY, IMG_RESIZED_W, IMG_RESIZED_H, null);
        }
        weapon.paintObject(g);
    }

    public void loadImage(int x, int y, int w, int h) {
        BufferedImage charSetImage = null;
        try {
            charSetImage = ImageIO.read(new File(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (charSetImage != null) {
            BufferedImage smallImg = charSetImage.getSubimage(x, y, w, h);

            // image for player facing right
            charImageRight = smallImg;
            BufferedImage leftImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = leftImage.createGraphics();
            g.drawImage(smallImg, 0, 0, w, h, w, 0, 0, h, null);
            g.dispose();

            charImageLeft = leftImage;
        }
    }
}
