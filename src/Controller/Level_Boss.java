package Controller;

import Object.Character.Enemy;
import Object.Character.Player;
import Object.Item.BossWeapon;
import Object.Item.Weapon;

import javax.swing.*;

public class Level_Boss extends Level {
    private final double ENEMY_SPEED = 2;

    public Level_Boss(Player player) {
        backgroundImage = new ImageIcon("Assets/castle.png").getImage();
        inventoryImage = loadImage("Assets/boss_inv.png");
        setHeartImages();
        gameState = GameState.LEVEL_BOSS;

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,1,1,0,0,0,0,3,0,0,0,0,0,1,1,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1},
                {1,0,0,0,1,1,0,0,0,0,0,4,0,0,0,0,0,0,1,1,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        //Set the player
        this.player = player;
        player.setWeapon(new Weapon(50,350, 12,false));
        player.getWeapon().changeImage(gameState);

        createLevel();
        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
        }

        //Create the puzzle chest
        //Create the enemies
    }
}
