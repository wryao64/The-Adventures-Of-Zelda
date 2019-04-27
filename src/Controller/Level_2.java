package Controller;

import Object.Character.Enemy;
import Object.Character.Player;

import javax.swing.*;

public class Level_2 extends Level {
    private final double ENEMY_SPEED = 1.5;

    public Level_2(Player player) {
        backgroundImage = new ImageIcon("Assets/floating.png").getImage();
        setHeartImages();
        gameState = GameState.LEVEL_2;

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,1,1,0,0,0,0,0,3,4,0,0,0,0,0,1,1,0,0,0,1},
                {1,2,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,2,0,1},
                {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1},
                {1,0,0,0,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        //Set the player
        this.player = player;
        player.addLives(3);

       createLevel();

        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
        }
    }
}
