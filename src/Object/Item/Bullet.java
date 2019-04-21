package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import Object.Object;

public class Bullet extends Object {

    double weaponPosX;

    double speedX;
    double range;


    public Bullet(double weaponPosX, double weaponPosY, double speedX, double range) {
        super(12,12,weaponPosX,weaponPosY);
        this.weaponPosX = weaponPosX;
        this.speedX = speedX;
        this.range = range;
    }


    public double getBulletPosX() { return posX; }

    public void moveShot() {
        posX = posX + speedX;
    }

    public void paintObject(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fill(new Rectangle2D.Double(posX, posY, width, height));
    }
}
