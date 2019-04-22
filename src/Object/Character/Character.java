package Object.Character;

import Object.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Character extends Object {
    public String imageLocation;

    BufferedImage charImageRight;
    BufferedImage charImageLeft;

    public Character(double w, double h, double x, double y){
        super(w,h,x,y);
    }

    protected double speedX, speedY;

    /*
    Horizontal movement of the character.
     */
    public void move(){
        posX = posX + speedX;
        posY = posY + speedY;
    }

    /*
    Getters and Setters for the speed of the character.
     */
    public void setSpeedX(double dx){ speedX = dx; }

    public void setSpeedY(double dy){ speedY = dy; }

    public double getSpeedX(){ return speedX; }

    public double getSpeedY(){ return speedY; }

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

            // flips image to face left
            BufferedImage leftImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = leftImage.createGraphics();
            g.drawImage(smallImg, 0, 0, w, h, w, 0, 0, h, null);
            g.dispose();

            charImageLeft = leftImage;
        }
    }

}
