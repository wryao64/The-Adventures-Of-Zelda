package Controller;

import Object.Platform;
import Object.Character.Enemy;
import Object.Character.Player;
import Object.Platform;
import Item.Puzzle;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
Class that handles the co-ordination between the player, the enemies on the screen and the platforms on the screen. Handles
the logistics of the level like the current score, which enemies are killed and the number of lives of the player.
 */
public abstract class Level{

    BufferedImage background;

    // Objects
    protected Player player;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Platform> platforms;
    protected Puzzle puzzle;

    protected int gravity = 1;
    protected int maxSpeedY = 5;

    /**
     * Collisions between different types of objects.
     */
    public void checkVertPlatformCollision(){
        for(Platform p : platforms){
            //Player collides with bottom of the platform i.e when jumping
            if(player.getBounds().intersects(p.getBottom()) && player.getSpeedY()<0){
                System.out.println("Collision vert bottom");
                player.setSpeedY(0);
            }
            //Player collides with the top of the platform i.e walking on it
            if(player.getBounds().intersects(p.getTop()) && player.getSpeedY()>0){
                System.out.println("Collision vert top");
                player.setSpeedY(0);
            }
        }
    }

    public void checkHorizPlatformCollision(){
        for(Platform p : platforms){
            //Player collides with the right edge of the platform
            if(player.getBounds().intersects(p.getRight()) && player.getSpeedX() < 0){
                System.out.println("Collision horiz right");
                player.setSpeedX(0);
            }
            //Player collides with the left edge of the platform
            if(player.getBounds().intersects(p.getLeft()) && player.getSpeedX() > 0){
                System.out.println("Collision horiz left");
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
                    //Controller.Game over
                }
            }
        }
    }

    /**
     * Manipulating the player. Used by the keylisteners in the gamescreen class.
     */
    public void setPlayerSpeedX(double dx){
        player.setSpeedX(dx);
    }

    public void setPlayerJump(){ player.jump(); }

    public double getMovementSpeed(){ return player.getMovementSpeed();}

    public void playercanJump(boolean jump) {player.setCanJump(jump);}


    /*
    Updating all the logic in the level/updates the values of the variables.
     */
    public void updateLevel(){
        //First check collisions with blocks and then move the player using updated x and y values.
        player.fall(gravity,maxSpeedY);
        checkHorizPlatformCollision();
        checkVertPlatformCollision();
        //checkEnemyCollision();
        player.move();
        System.out.println("Speed X " + player.getSpeedX());
    }


    /*
    Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(Graphics2D g){
        player.paint(g);
        for (Platform p : platforms){
            p.paint(g);
        }
    }

}
