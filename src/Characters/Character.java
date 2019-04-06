package Characters;

import java.awt.image.BufferedImage;

public class Character {

    protected int posX, posY, speedX, speedY;
    protected int width, height;
    protected int attackDamage;
    protected BufferedImage image;

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
