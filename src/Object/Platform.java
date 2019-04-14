package Object;

import Object.Object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Platform extends Object {

    public Platform(double w, double h, double x, double y){
        super(w,h,x,y);
    }

    public void paint(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
        g.setColor(Color.YELLOW);
        g.draw(getBottom());
        g.setColor(Color.GREEN);
        g.draw(getTop());
        g.setColor(Color.BLUE);
        g.draw(getRight());
        g.setColor(Color.PINK);
        g.draw(getLeft());

    }

}
