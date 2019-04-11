package Object;

import Object.Object;

import java.awt.*;

public class Platform extends Object {

    public Platform(int w, int h, int x, int y){
        super(w,h,x,y);
    }

    public void paint(Graphics2D g){
        g.setColor(Color.BLACK);
        g.fillRect(posX,posY,width,height);
    }

}
