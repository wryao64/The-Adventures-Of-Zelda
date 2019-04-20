package Object;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Object {
    private final int PADDING_WIDTH = 1;

    protected double posX, posY;
    protected double width, height;
    protected BufferedImage image;

    public Object (double w, double h, double x, double y){
        width = w;
        height = h;
        posX = x;
        posY = y;
    }

    public Object (double x, double y){
        posX = x;
        posY = y;
    }

    /*
    Collision areas for the object. Spans around the perimeter of the object.
     */

    public Rectangle2D getTop(){
        return new Rectangle2D.Double( posX+4, posY-1, width-8, 4);
    }

    public Rectangle2D getBottom(){
        return new Rectangle2D.Double(posX+4, posY + height-3, width-8, 4);
    }

    public Rectangle2D getLeft(){
        return new Rectangle2D.Double(posX+1, posY+4, 4, height-8);
    }

    public Rectangle2D getRight(){
        return new Rectangle2D.Double(posX +width -4, posY +4, 4, height-8);
    }

    public Rectangle2D getBottomLeftEdge() {
        return new Rectangle2D.Double(posX - PADDING_WIDTH, posY + height, PADDING_WIDTH, PADDING_WIDTH);
    }

    public Rectangle2D getRightEdge() {
        return new Rectangle2D.Double(posX + width, posY + height, PADDING_WIDTH, PADDING_WIDTH);
    }

    /**
     *Returns shape that spans the whole bounding box of the object.
    */
    public Rectangle2D getBounds(){
        return new Rectangle2D.Double(posX,posY,width,height);
    }


    /**
     * Abstract paint class all objects must implement.
     */
    public abstract void paintObject(Graphics2D g);

    /**
     * Getters and Setters.
    */
    public void setPosX(double x){
        posX = x;
    }

    public void setPosY(double y){
        posY = y;
    }

    public double getPosX(){ return posX; }

    public double getPosY(){ return posY; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public BufferedImage getImage(){ return image; }
}
