package Character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {

    protected int posX, posY;
    protected int speedX, speedY;
    protected int width, height;

    protected int attackDamage;

    protected BufferedImage image;

    /*
    Getters and Setters for the position and speed of the character.
     */
    public void setPosX(int x){
        posX = x;
    }

    public void setPosY(int y){
        posY = y;
    }

    public void setSpeedX(int dx){
        speedX = dx;
    }

    public void setSpeedY(int dy){
        speedY = dy;
    }

}
