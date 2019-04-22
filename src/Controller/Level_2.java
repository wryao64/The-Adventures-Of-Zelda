package Controller;

import Object.Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import javax.swing.*;

public class Level_2 extends Level {
    private final int ENEMY_SPEED = 2;

    public Level_2() {
        backgroundImage = new ImageIcon("Assets/floating.png").getImage();

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
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
        player = new Player(50,50,500,600);

        platforms = createPlatforms();

        // TODO: Create the puzzle chest

        enemies = createEnemies(GameState.LEVEL_2);
        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
        }
    }
}
