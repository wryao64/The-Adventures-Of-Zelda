package Object.Character;

import Controller.GameState;
import Object.Item.Weapon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Character {

    private final int IMAGE_WIDTH = 18;
    private final int IMAGE_HEIGHT = 11;
    private final int IMG_RESIZED_W = 55;
    private final int IMG_RESIZED_H = 40;

    private final int SPEED_Y = 0;
    private int moveCount = 0;
    private double shootFreq;

    //The initial random frequency the enemy will shoot.
    private double randFreq = (Math.random() * (31)) + (shootFreq-0.5);

    //Amount of score points the enemy is worth.
    protected int points;

    public Enemy(int w, int h, int x, int y, GameState level, Weapon weapon, int shootFreq, int health) {
        super(w,h,x,y);

        this.setImage(level);
        BufferedImage enemySheet = loadImage();
        SpriteSheet spriteSheet = new SpriteSheet(enemySheet,IMAGE_WIDTH,IMAGE_HEIGHT);

        //Adding the cut out of the sprites to the character movement arrays.
        charImagesRight.add(spriteSheet.getImage(7,42));
        charImagesRight.add(spriteSheet.getImage(39,42));
        charImagesRight.add(spriteSheet.getImage(71,42));
        charImagesRight.add(spriteSheet.getImage(103,42));

        hurtImageLeft = spriteSheet.getImage(39,106);
        hurtImageRight = spriteSheet.getFlippedImage(39,106);

        charImagesLeft.add(spriteSheet.getFlippedImage(7,42));
        charImagesLeft.add(spriteSheet.getFlippedImage(39,42));
        charImagesLeft.add(spriteSheet.getFlippedImage(71,42));
        charImagesLeft.add(spriteSheet.getFlippedImage(103,42));

        this.setSpeedY(SPEED_Y);
        super.weapon = weapon;
        this.shootFreq = shootFreq;
        this.health = health;
        System.out.println(getHealth());
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
            case LEVEL_BOSS:
                imageLocation = "Assets/enemy_white.png";
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


    /**
     * Paints enemy. Called by Level object.
     */
    @Override
    public void paintObject(Graphics2D g) {
        if(hurt){
            if(charDirection == 1) {
                setImageToPaint(hurtImageRight, 10);
            }else{
                setImageToPaint(hurtImageLeft,10);
            }
        }else {
            switchImages();
        }
        g.drawImage(imageToPaint, (int) posX, (int) posY, IMG_RESIZED_W,
                IMG_RESIZED_H, null);

        weapon.paintObject(g);
    }
}
