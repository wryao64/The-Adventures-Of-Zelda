package Object.Character;

import Controller.GameState;
import Object.Item.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Character {
    private final int START_X = 9;
    private final int START_Y = 39;
    private final int IMAGE_WIDTH = 18;
    private final int IMAGE_HEIGHT = 11;
    private final int IMG_RESIZED_W = 55;
    private final int IMG_RESIZED_H = 40;

    private final int SPEED_Y = 0;
    private int moveCount = 0;
    private double shootFreq;

    //The initial random frequency the enemy will shoot.
    private double randFreq = (Math.random() * (31)) + (shootFreq-0.5);
    protected int health;

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y, GameState level, Weapon weapon, int shootFreq, int health) {
        super(w,h,x,y);

        this.setImage(level);
        BufferedImage enemySheet = loadImage();
        SpriteSheet spriteSheet = new SpriteSheet(enemySheet,IMAGE_WIDTH,IMAGE_HEIGHT);

        //Adding the cut out of the sprites to the character movement arrays.
        charImagesRight.add(spriteSheet.getImage(7,41));
        charImagesRight.add(spriteSheet.getImage(39,41));
        charImagesRight.add(spriteSheet.getImage(71,41));
        charImagesRight.add(spriteSheet.getImage(103,41));

        charImagesLeft.add(spriteSheet.getFlippedImage(7,41));
        charImagesLeft.add(spriteSheet.getFlippedImage(39,41));
        charImagesLeft.add(spriteSheet.getFlippedImage(71,41));
        charImagesLeft.add(spriteSheet.getFlippedImage(103,41));

        this.setSpeedY(SPEED_Y);
        super.weapon = weapon;
        this.shootFreq = shootFreq;
        this.health = health;
        animSpeed = 10;
    }

    private void setImage(GameState level) {
        switch(level) {
            case TUTORIAL:
                imageLocation = "Assets/enemy_blue.png";
                break;
            case LEVEL_1:
                imageLocation = "Assets/enemy_red.png";
                break;
            case LEVEL_2:
                imageLocation = "Assets/enemy_green.png";
                break;
        }
    }

    public void move() {
        super.move();
        //Counter to keep track of when the enemy has to shoot again.
        moveCount++;
        weapon.moveShot();
        //Once the counter reaches the desired period, shoot the weapon again.
        if (moveCount > randFreq) {
            shootWeapon();
            //Randomly generate the count for when next time the enemy will shoot.
            randFreq = (Math.random() * (31)) + (shootFreq - 0.5);
            moveCount = 0;
        }
    }

    public int getPoints() { return points; }

    public int getHealth() { return health; }

    public void takeDamage(int damage){ health = health - damage; }

    /**
     * Paints enemy. Called by Level object.
     */
    @Override
    public void paintObject(Graphics2D g) {
       switchImages();
        g.drawImage(imageToPaint, (int) posX, (int) posY, IMG_RESIZED_W,
                IMG_RESIZED_H, null);

        g.setColor(Color.RED);
        g.drawString(health+"", (int)(posX +(width/2)), (int)(posY+(height/2)));
        weapon.paintObject(g);
    }
}
