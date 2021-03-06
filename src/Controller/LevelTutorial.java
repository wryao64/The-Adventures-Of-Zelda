package Controller;

import Object.Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelTutorial extends Level {
    public static final String IMAGE_LOCATION = "Resources/Assets/";

    BufferedImage tutorialTip1;
    BufferedImage tutorialTip2;
    BufferedImage tutorialTip3;

    private final double ENEMY_SPEED = 1;

    public LevelTutorial(Player player) {
        backgroundImage = new ImageIcon(IMAGE_LOCATION + "icy_mountains.png").getImage();
        inventoryImage = loadImage(IMAGE_LOCATION + "tutorial_inv.png");
        tutorialTip1 = loadImage(IMAGE_LOCATION + "tutorialTip1.png");
        tutorialTip2 = loadImage(IMAGE_LOCATION + "tutorialTip2.png");
        tutorialTip3 = loadImage(IMAGE_LOCATION + "tutorialTip3.png");

        this.setHeartImages();

        gameState = GameState.TUTORIAL;

        tileMap = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        //Set the player
        this.player = player;
        this.player.setWeapon(new Weapon(20, 250, 7, false));

        this.createLevel();

        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
            e.getWeapon().setRange(200);
            e.setShootFreq(2.5);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void paintLevel(Graphics2D g) {
        super.paintLevel(g);
        g.drawImage(tutorialTip1, 100, 650, 170, 50, null);
        g.drawImage(tutorialTip2, 500, 450, 170, 50, null);
        g.drawImage(tutorialTip3, 250, 150, 170, 50, null);
    }
}
