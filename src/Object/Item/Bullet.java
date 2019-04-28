package Object.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

import Controller.GameState;
import Object.Character.SpriteSheet;
import Object.Object;

public class Bullet extends Object {
    public static final String IMAGE_LOCATION = "Resources/Assets/";

    double weaponPosX;

    double speedX = 0;
    double speedY = 0;
    double range;

    private SpriteSheet spriteSheet;
    private BufferedImage image;

    public Bullet(double weaponPosX, double weaponPosY, double speedX, double range, GameState gameState, Boolean enemy) {
        super(12, 12, weaponPosX, weaponPosY);
        this.weaponPosX = weaponPosX;
        this.speedX = speedX;
        this.range = range;
        this.setImage(gameState, enemy);
    }

    public Bullet(double weaponPosX, double weaponPosY, double speedX, double speedY, double range, GameState gameState, Boolean enemy) {
        super(12, 12, weaponPosX, weaponPosY);
        this.weaponPosX = weaponPosX;
        this.speedX = speedX;
        this.speedY = speedY;
        this.range = range;
        this.setImage(gameState, enemy);
    }

    public double getBulletPosX() {
        return posX;
    }


    public void moveShot() {
        posX = posX + speedX;
        posY = posY + speedY;
    }

    public void paintObject(Graphics2D g) {
        g.drawImage(image, (int) posX, (int) posY, 30, 30, null);
    }

    private void setImage(GameState level, Boolean enemy) {
        switch (level) {
            case TUTORIAL:
                if (!enemy) {
                    imageLocation = IMAGE_LOCATION + "bulletTutorial.png";
                    image = loadImage();

                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                } else {
                    imageLocation = IMAGE_LOCATION + "enemyTBullet.png";
                    image = loadImage();
                }
                break;
            case LEVEL_1:
                if (!enemy) {
                    imageLocation = IMAGE_LOCATION + "level1Bullet.png";
                    image = loadImage();

                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                } else {
                    imageLocation = IMAGE_LOCATION + "enemy1Bullet.png";
                    image = loadImage();
                }
                break;
            case LEVEL_2:
                if (!enemy) {
                    imageLocation = IMAGE_LOCATION + "level2Bullet.png";
                    image = loadImage();

                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                } else {
                    imageLocation = IMAGE_LOCATION + "enemy2Bullet.png";
                    image = loadImage();
                }
                break;
            case LEVEL_BOSS:
                if (!enemy) {
                    imageLocation = IMAGE_LOCATION + "bossBullet.png";
                    image = loadImage();

                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                } else {
                    imageLocation = IMAGE_LOCATION + "enemyBossBullet.png";
                    image = loadImage();
                }
                break;
        }
    }
}
