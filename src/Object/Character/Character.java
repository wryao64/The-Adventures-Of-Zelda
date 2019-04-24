package Object.Character;

import Object.Item.Weapon;
import Object.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Character extends Object {
    public String imageLocation;

    protected ArrayList<BufferedImage> charImagesRight = new ArrayList<>();
    protected ArrayList<BufferedImage> charImagesLeft = new ArrayList<>();
    protected BufferedImage imageToPaint;
    protected BufferedImage hurtImageLeft;
    protected BufferedImage hurtImageRight;

    protected int health;
    protected boolean hurt = false;
    protected int hurtImageCount = 0;

    protected int charDirection = 1;
    protected Weapon weapon;
    protected int movementCount = 0;
    protected int animSpeed = 1;
    protected double speedX, speedY;

    public Character(double w, double h, double x, double y){
        super(w,h,x,y);
    }

    /*
    Horizontal movement of the character.
     */
    public void move(){
        posX = posX + speedX;
        posY = posY + speedY;
    }

    public void shootWeapon(){
        weapon.shoot(posX,posY,charDirection);
    }

    /**
     * Iterates through the movement images for the character to create walking animation effect.
     */
    public void switchImages() {
            movementCount++;
            if (charDirection == 1) {
                if (movementCount >= charImagesRight.size() * animSpeed) {
                    movementCount = 0;
                }
                imageToPaint = charImagesRight.get(movementCount / animSpeed);
            } else {
                if (movementCount >= charImagesLeft.size() * animSpeed) {
                    movementCount = 0;
                }
                imageToPaint = charImagesLeft.get(movementCount / animSpeed);
            }
    }

    public void setImageToPaint(BufferedImage image, int speed) {
        if (hurtImageCount < speed) {
            hurtImageCount++;
            imageToPaint = image;
        } else {
            hurt = false;
            hurtImageCount = 0;
        }
    }
    /*
    Getters and Setters for the speed of the character.
     */
    public void setSpeedX(double dx){ speedX = dx; }

    public void setSpeedY(double dy){ speedY = dy; }

    public double getSpeedX(){ return speedX; }

    public double getSpeedY(){ return speedY; }

    public void setDir(int direction) { charDirection = direction; }

    public int getDir() { return charDirection;}

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public Weapon getWeapon() { return weapon; }

    public void setHealth(int health) { this.health = health; }

    public int getHealth() { return health; }

    public void takeDamage(int damage){ health = health - damage; hurt = true; }


    /**
     * Loads the sprite sheet for the given character.
     */
    public BufferedImage loadImage() {
        BufferedImage charSetImage = null;
        try {
            charSetImage = ImageIO.read(new File(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charSetImage;
    }

}
