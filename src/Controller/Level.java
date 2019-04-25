package Controller;

import Object.Character.SpriteSheet;
import Object.Item.Bullet;
import Object.Item.Weapon;
import Object.Platform;
import Object.Character.Enemy;
import Object.Character.Player;
import Object.Item.Puzzle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private static final String ENEMY_HIT_SOUND = Sound.SOUND_LOCATION + "enemy_hit.wav";

    Image backgroundImage;

    protected Player player;
    protected ArrayList<Enemy> enemies = new ArrayList<>();
    protected ArrayList<Platform> platforms = new ArrayList<>();
    protected Puzzle puzzle;
    protected GameState gameState;
    protected boolean puzzleOpened = false;

    protected int gravity = 1;
    protected int maxSpeedY = 15;
    protected int[][] tileMap;

    protected ArrayList<BufferedImage> heartImages = new ArrayList<>();

    /**
     * Collisions between platforms and the player.
     */
    public void checkPlatformCollisions() {
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
                //Checks if the up key is continously pressed. Prevents double jumps
                if(player.getJumpKeyPressed() == false) {
                    playerCanJump(true);
                }
            }
            //Player collides with the right edge of the platform
            if(player.getBounds().intersects(p.getRight()) && player.getSpeedX() < 0){
                player.setSpeedX(0);
            }
            //Player collides with the left edge of the platform
            if(player.getBounds().intersects(p.getLeft()) && player.getSpeedX() > 0) {
                player.setSpeedX(0);
            }
            bulletPlatformCollision(p);
        }
    }

    /**
     * Checks if the enemy's shots hit the player.
     */
    public void checkPlayerEnemyCollisions() {

        ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
          Enemy enemyToRemove;
          enemyToRemove = handlePlayerHitEnemy(e);
          handleEnemyHitPlayer(e);

            if(enemyToRemove != null){
                enemiesToRemove.add(enemyToRemove);
            }
        }
        for(Enemy e : enemiesToRemove) {
            enemies.remove(e);
        }

    }

    /**
     * Checks and handles when the bullets of the player hits an enemy.
     */
    private Enemy handlePlayerHitEnemy(Enemy e) {
        ArrayList<Bullet> bullets = player.getWeapon().getBullets();
        Bullet bulletToRemove = null;
        Enemy enemyToRemove = null;

        for (Bullet b: bullets) {
            if(b.getBounds().intersects(e.getBounds())){
                bulletToRemove = b;
                e.takeDamage(player.getWeapon().getAttackDamage());

                // Sound of enemy being hit
                Sound.playSound(ENEMY_HIT_SOUND);

                if(e.getHealth() <= 0){
                    enemyToRemove = e;
                    player.addToEnemiesKilled();
                }
            }
        }
        if(bulletToRemove != null){
            player.getWeapon().removeBullet(bulletToRemove);
        }
       return enemyToRemove;
    }

    /**
     * Checks and handles when the bullets an enemy hits a player.
     */
    private void handleEnemyHitPlayer(Enemy e) {
        ArrayList<Bullet> bullets = e.getWeapon().getBullets();
        Bullet bulletToRemove = null;
        for (Bullet b: bullets) {
            if(b.getBounds().intersects(player.getBounds())){
                bulletToRemove = b;
                if(!player.hurt()){
                    player.takeDamage(e.getWeapon().getAttackDamage());
                    if(player.getHealth()<=0){
                        player.loseLife();
                    }
                }
            }
        }
        if(bulletToRemove != null) {
            e.getWeapon().removeBullet(bulletToRemove);
        }
    }

    /**
     * Checks and handles when a bullet hits a platform or wall
     */
    private void bulletPlatformCollision(Platform p) {
        ArrayList<Bullet> bulletsPlayer = player.getWeapon().getBullets();
        Bullet bulletToRemovePlayer = null;

        for (Bullet b: bulletsPlayer) {
            if(b.getBounds().intersects(p.getBounds())){
                bulletToRemovePlayer = b;
            }
        }
        if(bulletToRemovePlayer != null) {
            player.getWeapon().removeBullet(bulletToRemovePlayer);
        }

        for (Enemy e : enemies) {
            Bullet bulletToRemoveEnemy = null;
            ArrayList<Bullet> bulletsEnemy = e.getWeapon().getBullets();
            for (Bullet b : bulletsEnemy) {
                if (b.getBounds().intersects(p.getBounds())) {
                    bulletToRemoveEnemy = b;
                }
            }
            if (bulletToRemoveEnemy != null) {
                 e.getWeapon().removeBullet(bulletToRemoveEnemy);
            }
        }
    }

    public void checkPuzzleCollision() {
        if(player.getBounds().intersects(puzzle.getBounds())){
            puzzleOpened = true;
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

    public void setPlayerDirection( int direction) { player.setDir(direction); }

    public void setSideKeyPressed(boolean pressed) { player.setKeyPressed(pressed); }

    public void setJumpKeyPressed(boolean pressed) { player.setJumpKeyPressed(pressed); }

    public boolean getPuzzleStatus() { return puzzleOpened; }

    public Player getPlayer() { return player; }

    /**
     * Updating all the logic in the level/updates the values of the variables.
     */
    public void updateLevel() {
        //First check collisions with blocks and then move the player using updated x and y values.
        player.fall(gravity,maxSpeedY);
        checkPlatformCollisions();
        checkPlayerEnemyCollisions();
        checkPuzzleCollision();
        player.move();
        enemyMove();
    }

    /**
     * Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
        paintHearts(g);

        player.paintObject(g);
        puzzle.paintObject(g);

        for (Platform p : platforms){
            p.paintObject(g);
        }
        for (Enemy e : enemies) {
            e.paintObject(g);
        }

    }

    /**
     * Used to draw the platforms from the particular tilemap of the level.
     */
    public void createLevel(){
        for (int i  = 0; i<24; i++){
            for (int j = 0; j<15; j++){
                if(tileMap[j][i] == 1){
                    Platform newPlatform = new Platform(i *50, (j + 1) * 50);
                    platforms.add(newPlatform);
                }else if(tileMap[j][i] == 2){
                    Enemy newEnemy = new Enemy(55, 40, i * 50, (j + 1) * 50 + 10, gameState,
                            new Weapon(50,300,5),2,70);
                    enemies.add(newEnemy);
                }else if(tileMap[j][i] == 3) {
                    puzzle = new Puzzle(i*50, (j+1)*50+20,50,30);
                }else if(tileMap[j][i] == 4) {
                    player.setPosX(i*50);
                    player.setPosY((j+1)*50);
                    player.setInitPosition(i*50,(j+1)*50);
                }
            }
        }
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
                    int dir = e.getDir();
                    e.setDir(- dir); // reverse direction
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
            if (e.getDir() == 1) {
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

    public void setHeartImages() {
        String imageLocation = "Assets/hearts.png";
        try {
            BufferedImage heartSheet = ImageIO.read(new File(imageLocation));
            SpriteSheet ss = new SpriteSheet(heartSheet,50,50);
            heartImages.add(ss.getImage(0,0));
            heartImages.add(ss.getImage(50,0));
            heartImages.add(ss.getImage(100,0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintHearts(Graphics2D g) {
        int lives = player.getLives();
        boolean halfHeart = player.getHealth() < player.getMaxHealth() && player.getHealth()>0;
        for(int i=0; i < lives; i++) {
            if (halfHeart && i == lives - 1) {
                g.drawImage(heartImages.get(0), 50 * i + 50, 100, 50,
                        50, null);
            } else
                g.drawImage(heartImages.get(1), 50 * i+50, 100, 50,
                        50, null);
        }
    }
}
