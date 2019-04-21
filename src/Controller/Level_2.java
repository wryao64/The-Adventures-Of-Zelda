package Controller;

import Object.Item.Weapon;
import Object.Character.Enemy;
import Object.Character.Player;

import java.util.ArrayList;

public class Level_2 extends Level {
    private final int ENEMY_SPEED = 2;

    public Level_2() {
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
                {1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
                {1,0,0,0,0,0,0,2,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        //Set the player
        player = new Player(50,50,500,600);

        platforms = createPlatforms();

        // TODO: Create the puzzle chest

        enemies = createEnemies();
        for (Enemy e : enemies) {
            e.setSpeedX(ENEMY_SPEED);
        }
    }

    public ArrayList<Enemy> createEnemies() {

        ArrayList<Enemy> enemies = new ArrayList<>();

        for (int i  = 0; i<24; i++){
            for (int j = 0; j<15; j++){
                if(tileMap[j][i] == 2){
                    Enemy newEnemy = new Enemy(50, 50, i * 50, (j + 1) * 50,
                            new Weapon(10,250,5),2,50);
                    enemies.add(newEnemy);
                }
            }
        }
        return enemies;
    }
}
