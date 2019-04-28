package Controller;

import Object.Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import javax.swing.*;

public class Level_1 extends Level {
    private final double ENEMY_SPEED = 1.5;

    public Level_1(Player player) {
        backgroundImage = new ImageIcon("Assets/volcanic_mountains.png").getImage();
        inventoryImage = loadImage("Assets/lvl1_inv.png");
        setHeartImages();

        gameState = GameState.LEVEL_1;

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,2,0,0,0,3,0,0,0,2,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1},
                {1,0,0,1,0,0,0,0,0,2,0,0,0,2,0,0,0,0,0,0,0,0,0,1},
                {1,0,4,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        this.player = player;
        player.setHealth(60);

        //Upgrading the players weapon.
        player.setWeapon(new Weapon(20,350, 7,false));
        player.getWeapon().setMaxBullets(2);
        player.getWeapon().changeImage(gameState);

        createLevel();

        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
            e.getWeapon().setRange(300);
            e.setShootFreq(1.5);
        }
    }
}
