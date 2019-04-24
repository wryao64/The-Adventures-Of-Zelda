package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import Object.Object;

public class Puzzle extends Object{

    public Puzzle(double posX, double posY, double w, double h) {
          super(w,h,posX,posY);
    }

    public void paintObject(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.fill(new Rectangle2D.Double(posX, posY,width, height));
    }
}
