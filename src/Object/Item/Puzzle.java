package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import Object.Object;

public class Puzzle extends Object{

    public Puzzle(double posX, double posY, double w, double h) {
        super(w,h,posX,posY);
        imageLocation = "Assets/chest.png";
        image = loadImage();
    }

    public void paintObject(Graphics2D g){
        g.drawImage(image, (int) posX, (int) posY, (int)width,
                (int)height, null);
    }
}
