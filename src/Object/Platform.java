package Object;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Platform extends Object {
   // private final int width = 50;
   // private final int height = 50;

    public Platform(double x, double y){
        super(50,50,x,y);
    }

    public void paintObject(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));

       /* g.setColor(Color.YELLOW);
        g.draw(getTop());
        g.draw(getBottom());
        g.draw(getRight());
        g.draw(getLeft());*/
    }


}
