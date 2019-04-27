package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import Controller.Game;
import Controller.GameState;
import Object.Object;

public class Puzzle extends Object{

    public Puzzle(double posX, double posY, double w, double h, GameState gameState) {
        super(w, h, posX, posY);
        if (gameState == GameState.LEVEL_BOSS) {
            imageLocation = "Assets/cage.png";
        } else {
            imageLocation = "Assets/chest.png";
        }
        image = loadImage();
    }


    public void paintObject(Graphics2D g){
        g.drawImage(image, (int) posX, (int) posY, (int)width,
                (int)height, null);
    }
}
