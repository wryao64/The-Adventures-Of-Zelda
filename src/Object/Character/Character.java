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

    public void loadImage(int w, int h, int resized_w, int resized_h) {
        BufferedImage charSetImage = null;
        try {
            charSetImage = ImageIO.read(new File(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (charSetImage != null) {
            BufferedImage smallImg = charSetImage.getSubimage(4, 4, w, h);

            // resize image
            Image tmp = smallImg.getScaledInstance(resized_w, resized_h, Image.SCALE_SMOOTH);
            BufferedImage scaledImg = new BufferedImage(resized_w, resized_h, smallImg.getType());

            Graphics2D g2d = scaledImg.createGraphics();
            g2d.drawImage(tmp, 0, 0, resized_w, resized_h, null);
            g2d.dispose();

            // image for player facing right
            charImageRight = scaledImg;

            // flips image to face left
            BufferedImage leftImage = new BufferedImage(resized_w, resized_h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = leftImage.createGraphics();
            g.drawImage(scaledImg, 0, 0, resized_w, resized_h,
                    resized_w, 0, 0, resized_h, null);
            g.dispose();

            charImageLeft = leftImage;
        }
    }

}
