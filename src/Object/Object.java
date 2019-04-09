package Object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    protected int posX, posY;
    protected int width, height;
    protected BufferedImage image;

    public Object (int w, int h, int x, int y){
        width = w;
        height = h;
        posX = x;
        posY = y;
    }

    /*
    Collision areas for the platform. Spans around the perimeter of the object.
     */

    public Rectangle getTop(){
        return new Rectangle((int) posX +4, (int) posY-1, width-8, 4);
    }

    public Rectangle getBottom(){
        return new Rectangle((int) posX +4, (int) posY + height-3, width-8, 4);
    }

    public Rectangle getLeft(){
        return new Rectangle((int) posX +1, (int) posY +4, 4, height-8);
    }

    public Rectangle getRight(){
        return new Rectangle((int) posX +width -4, (int) posY+4, 4, height-8);
    }

    /*
    Returns shape that spans the whole bounding box of the object.
     */
    public Rectangle getBounds(){
        return new Rectangle(posX,posY,width,height);
    }

    /*
    Abstract??
     */
    public void paintObject(){ }

    /*
   Getters and Setters.
    */
    public void setPosX(int x){
        posX = x;
    }

    public void setPosY(int y){
        posY = y;
    }

    public int getPosX(){ return posX; }

    public int getPosY(){ return posY; }


    public BufferedImage getImage(){ return image; }
}
