
import Object.Platform;
import Object.Character.Enemy;
import Object.Character.Player;
import Object.Platform;
import Item.Puzzle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
Class that handles the co-ordination between the player, the enemies on the screen and the platforms on the screen. Handles
the logistics of the level like the current score, which enemies are killed and the number of lives of the player.
 */
public class Level{

    BufferedImage background;

    // Objects
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Platform> platforms;
    private Puzzle puzzle;

    private static final int gravity = 1;
    private static final int maxSpeedY = 5;

    public void Level(Player player, ArrayList<Enemy> enemies, ArrayList<Platform> platforms,
                      Puzzle puzzle){
        this.player = player;
        this.enemies = enemies;
        this.platforms = platforms;
        this.puzzle = puzzle;
    }

    /*
    Collisions between different types of objects.
     */

    public void checkVertPlatformCollision(){
        for(Platform p : platforms){
            if(player.getBounds().intersects(p.getBottom()) && player.getSpeedY()<0){
                player.setSpeedY(0);
            }
            if(player.getBounds().intersects(p.getTop()) && player.getSpeedY()>0){
                player.setSpeedY(0);
            }
        }
    }

    public void checkHorizPlatformCollision(){
        for(Platform p : platforms){
            if(player.getBounds().intersects(p.getRight()) && player.getSpeedX() < 0){
                player.setSpeedX(0);
            }
            if(player.getBounds().intersects(p.getLeft()) && player.getSpeedX() > 0){
                player.setSpeedX(0);
            }
        }
    }

    public void checkEnemyCollision() {
        for (Enemy e : enemies) {
            if (player.getBounds().intersects(e.getBounds())) {
                player.loseLife();
                if(player.getLives() > 0){
                    //Reset the player to the start position
                    player.initPosition();
                }else {
                    //Game over
                }
            }
        }
    }

    /*
    Manipulating the player. Used by the keylisteners
     */
    public void setPlayerSpeedX(int dx){
        player.setSpeedX(dx);
    }

    public void setPlayerSpeedY(int dy){
        player.setSpeedY(dy);
        }

    /*
    Updating all the logic in the level/updates the values of the variables.
     */

    public void updateLevel(){
        checkHorizPlatformCollision();
        checkVertPlatformCollision();
        checkEnemyCollision();
        player.move();
        player.fall(gravity,maxSpeedY);
    }

    /*
    Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(){
        //player.paint()

        for (Platform p : platforms){
            //p.paint
        }
        for (Enemy e : enemies){
            //e.paint
        }
    }

}
