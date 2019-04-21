package Controller;

import Object.Platform;
import Object.Character.Enemy;
import Object.Character.Player;
import Item.Puzzle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Class that handles the co-ordination between the player, the enemies on the screen and the platforms on the screen.
 * Handles the logistics of the level like the current score, which enemies are killed and the number of lives of the player.
 */
public abstract class Level {
    private final int IMAGE_HEIGHT = 800;
    private final int IMAGE_WIDTH = 1200;
    private final int PLATFORM_SIZE = 50;
    private final int DIFF = 5;

    Image backgroundImage;

    // Objects
    protected Player player;
    protected ArrayList<Enemy> enemies;
    protected ArrayList<Platform> platforms;
    protected Puzzle puzzle;

    //Gravity
    protected int gravity = 1;
    protected int maxSpeedY = 15;
    protected int[][] tileMap;

    /**
     * Collisions between different types of objects.
     */
    public void checkVertPlatformCollision() {
        for(Platform p : platforms){
            //Player collides with bottom of the platform i.e when jumping
            if(player.getBounds().intersects(p.getBottom()) && player.getSpeedY()<0){
                player.setSpeedY(0);
                player.setPosY(p.getPosY()+ p.getHeight());
            }
            //Player collides with the top of the platform i.e walking on it
            if(player.getBounds().intersects(p.getTop()) && player.getSpeedY()>0){
                player.setSpeedY(0);
                player.setPosY(p.getPosY()-player.getHeight());
                playerCanJump(true);
            }
        }
    }

    public void checkHorizPlatformCollision() {
        for(Platform p : platforms){
            //Player collides with the right edge of the platform
            if(player.getBounds().intersects(p.getRight()) && player.getSpeedX() < 0){
                player.setSpeedX(0);
            }
            //Player collides with the left edge of the platform
            if(player.getBounds().intersects(p.getLeft()) && player.getSpeedX() > 0) {
                player.setSpeedX(0);
            }
        }
    }

    /**
     * Checks if the enemy's shots hit the player.
     */
    public void checkEnemyShotCollision() {
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
     * Checks if the players shots hit the enemies.
     */
    public void checkPlayerShotCollision() {
        /*for (Enemy e : enemies) {
            if (player.getWeapon().getBounds().intersects(e.getBounds())) {
                e.takeDamage(player.giveDamage());
                //remove enemy from the arraylist?
                if(e.getHealth() < 0) {
                    enemies.remove(e);
                    //Add the points the enemy is worth to the players score.
                    player.addToScore(e.getPoints());
                }
            }
        }*/

        for (Platform p : platforms) {
            if (player.getWeapon().getBounds().intersects(p.getBounds())) {
                player.getWeapon().setWeaponShot(false);
            }
        }
    }
  
    /**
     * Manipulating the player. Used by the keyListeners in the GameScreen class.
     */

    public void setPlayerSpeedX(double dx){ player.setSpeedX(dx); }

    public void setPayerSpeedY(double dy) { player.setSpeedY(dy); }


    public void setPlayerJump() { player.jump(); }

    public void setPlayerShoot() { player.shootWeapon();}

    public double getMovementSpeed() { return player.getMovementSpeed();}

    public void playerCanJump(boolean jump) { player.setCanJump(jump);}

    public void setPlayerDirection( int direction) { player.setPlayerDir(direction); }

    /**
     * Updating all the logic in the level/updates the values of the variables.
     */
    public void updateLevel() {
        //First check collisions with blocks and then move the player using updated x and y values.
        player.fall(gravity,maxSpeedY);
        checkHorizPlatformCollision();
        checkVertPlatformCollision();
        checkPlayerShotCollision();
        //checkEnemyCollision();
        player.move();
        enemyMove();
    }

    /**
     * Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);

        player.paintObject(g);
        for (Platform p : platforms){
            p.paintObject(g);
        }

        if (enemies != null) {
            for (Enemy e : enemies) {
                e.paintObject(g);
            }
        }
    }

    /**
     * Used to draw the platforms from the particular tilemap of the level.
     */
    public ArrayList<Platform> createPlatforms(){
        ArrayList<Platform> platforms = new ArrayList<Platform>();

        for (int i  = 0; i<24; i++){
            for (int j = 0; j<15; j++){
                if(tileMap[j][i] == 1){
                    Platform newPlatform = new Platform(i *50, (j + 1) * 50);
                    platforms.add(newPlatform);
                }
            }
        }
        return platforms;
    }

    public ArrayList<Enemy> createEnemies() {

        ArrayList<Enemy> enemies = new ArrayList<>();

        for (int i  = 0; i<24; i++){
            for (int j = 0; j<15; j++){
                if(tileMap[j][i] == 2){
                    Enemy newEnemy = new Enemy(50, 50, i * 50, (j + 1) * 50);
                    enemies.add(newEnemy);
                }
            }
        }
        return enemies;
    }

    /**
     * Enemy movement
     */
    private void enemyMove() {
        if (enemies != null) {
            for (Enemy e : enemies) {
                // finds next platform the enemy will move to
                Platform p = findPlatform(e);

                if (p == null) { // no further platform
                    int dir = e.getDirection();
                    e.setDirection(1 - dir); // reverse direction
                    e.setSpeedX(-e.getSpeedX());
                }

                e.move();
            }
        }
    }

    /**
     * Finds the next platform the enemy will move to
     * @param e Enemy object
     * @return Next platform the enemy will move to. Null if no platform.
     */
    private Platform findPlatform(Enemy e) {
        for (Platform p : platforms) {
            // moving right
            if (e.getDirection() == 1) {
                if (p.getBounds().intersects(e.getRight())) {
                    return null;
                }
                if (p.getBounds().intersects(e.getRightEdge())) {
                    return p;
                }
            } else { // moving left
                if (p.getBounds().intersects(e.getLeft())) {
                    return null;
                }
                if (p.getBounds().intersects(e.getBottomLeftEdge())) {
                    return p;
                }
            }
        }
        return null;
    }
}
