package Object.Item;

import java.awt.*;

import Controller.GameState;
import Object.Object;

public class Puzzle extends Object {
    public static final String IMAGE_LOCATION = "Resources/Assets/";

    public Puzzle(double posX, double posY, double w, double h, GameState gameState) {
        super(w, h, posX, posY);

        if (gameState == GameState.LEVEL_BOSS) {
            imageLocation = IMAGE_LOCATION + "cage.png";
        } else {
            imageLocation = IMAGE_LOCATION + "chest.png";
        }

        image = loadImage();
    }

    public void paintObject(Graphics2D g) {
        g.drawImage(image, (int) posX, (int) posY, (int) width, (int) height, null);
    }
}
