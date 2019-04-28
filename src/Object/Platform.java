package Object;

import Controller.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Platform extends Object {

    BufferedImage platformImage;
    BufferedImage cornerPlatformImage;

    Boolean corner;

    public Platform(double x, double y, GameState gameState, Boolean corner) {
        super(50, 50, x, y);

        this.setImage(gameState, corner);
        platformImage = loadImage();
    }

    private void setImage(GameState level, Boolean corner) {
        switch (level) {
            case TUTORIAL:
                if (corner) {
                    imageLocation = "Assets/tutorialCorner.png";
                } else {
                    imageLocation = "Assets/tutorialTile.png";
                }
                break;
            case LEVEL_1:
                if (corner) {
                    imageLocation = "Assets/level1Corner.png";
                } else {
                    imageLocation = "Assets/level1Tile.png";
                }
                break;
            case LEVEL_2:
                if (corner) {
                    imageLocation = "Assets/level2Corner.png";
                } else {
                    imageLocation = "Assets/level2Tile.png";
                }
                break;
            case LEVEL_BOSS:
                if (corner) {
                    imageLocation = "Assets/bossCorner.png";
                } else {
                    imageLocation = "Assets/bossTile.png";
                }
                break;
        }
    }

    public void paintObject(Graphics2D g) {
        g.drawImage(platformImage, (int) posX, (int) posY, (int) width, (int) height, null);
    }
}
