package Controller;

import Object.Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Level_Tutorial extends Level {
    BufferedImage tutorialTip1;
    BufferedImage tutorialTip2;
    BufferedImage tutorialTip3;

    private final double ENEMY_SPEED = 1;

    public Level_Tutorial(Player player) {
        backgroundImage = new ImageIcon("Assets/icy_mountains.png").getImage();
        inventoryImage = loadImage("Assets/tutorial_inv.png");
        tutorialTip1 = loadImage("Assets/tutorialTip1.png");
        tutorialTip2 = loadImage("Assets/tutorialTip2.png");
        tutorialTip3 = loadImage("Assets/tutorialTip3.png");

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
        g.drawImage(tutorialTip1, 100, 650, 150, 50, null);
        g.drawImage(tutorialTip2, 500, 450, 150, 50, null);
        g.drawImage(tutorialTip3, 250, 150, 150, 50, null);
    }
}
