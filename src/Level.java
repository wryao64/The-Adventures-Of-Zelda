
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


    private int gravity = 1;
    private int maxSpeedY = 5;

    public boolean verticalPlatformCollision(){
        for(Platform p : platforms){
            if(player.getBounds().intersects(p.getBottom()) && player.getSpeedY()<0){
                player.setSpeedY(0);
                return true;
            }
            if(player.getBounds().intersects(p.getTop()) && player.getSpeedY()>0){
                player.setSpeedY(0);
                return true;
            }
        }return false;
    }

    public boolean horizontalPlatformCollision(){
        for(Platform p : platforms){
            if(player.getBounds().intersects(p.getRight()) && player.getSpeedX() < 0){
                player.setSpeedX(0);
                return true;
            }
            if(player.getBounds().intersects(p.getLeft()) && player.getSpeedX() > 0){
                player.setSpeedX(0);
                return true;
            }
        }return false;
    }

    public void setPlayerSpeedX(int dx){
        player.setSpeedX(dx);
    }

    public void playerJump(){
        player.jump();
    }

    public void playerFall(){
        player.fall(gravity, maxSpeedY);
    }

    /*
    Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(){
        /*
        Iterate through ArrayLists and paint objects in the ArrayList one by one.
         */

    }

}
