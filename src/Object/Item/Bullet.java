package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import Controller.GameState;
import Object.Character.SpriteSheet;
import Object.Object;

public class Bullet extends Object {

    double weaponPosX;

    double speedX = 0;
    double speedY = 0;
    double range;

    private SpriteSheet spriteSheet;

    private BufferedImage image;


    public Bullet(double weaponPosX, double weaponPosY, double speedX, double range,GameState gameState,Boolean enemy) {
        super(12,12,weaponPosX,weaponPosY);
        this.weaponPosX = weaponPosX;
        this.speedX = speedX;
        this.range = range;
        setImage(gameState,enemy);
    }

    public Bullet(double weaponPosX, double weaponPosY, double speedX,double speedY, double range, GameState gameState,Boolean enemy) {
        super(12,12,weaponPosX,weaponPosY);
        this.weaponPosX = weaponPosX;
        this.speedX = speedX;
        this.speedY = speedY;
        this.range = range;
        setImage(gameState,enemy);

    }


    public double getBulletPosX() { return posX; }

    public double getRange() { return range; }

    public void moveShot() {
        posX = posX + speedX;
        posY = posY + speedY;
    }

    private void setImage(GameState level,Boolean enemy) {
        System.out.println(level);
        switch(level) {
            case TUTORIAL:
                if(!enemy) {
                    imageLocation = "Assets/bulletTutorial.png";
                    image = loadImage();
                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                }else {
                        imageLocation = "Assets/enemyTBullet.png";
                        image = loadImage();
                }
                break;
            case LEVEL_1:
                if(!enemy){
                    imageLocation = "Assets/level1Bullet.png";
                    image = loadImage();
                        if(speedX>0) {
                            spriteSheet = new SpriteSheet(image,50,50);
                            image = spriteSheet.getFlippedImage(0,0);
                        }
                }else{
                    imageLocation = "Assets/enemy1Bullet.png";
                    image = loadImage();
                }
                break;
            case LEVEL_2:
                if(!enemy){
                    imageLocation = "Assets/level2Bullet.png";
                    image = loadImage();
                    if(speedX>0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                }else{
                    imageLocation = "Assets/enemy2Bullet.png";
                    image = loadImage();
                }
                break;
            case LEVEL_BOSS:
                if(!enemy) {
                    imageLocation = "Assets/bossBullet.png";
                    image = loadImage();
                    if (speedX > 0) {
                        spriteSheet = new SpriteSheet(image, 50, 50);
                        image = spriteSheet.getFlippedImage(0, 0);
                    }
                }else{
                    imageLocation = "Assets/enemyBossBullet.png";
                    image = loadImage();
                }
                break;

        }
    }

    public void paintObject(Graphics2D g) {
        g.drawImage(image, (int) posX, (int) posY, 30,
                30, null);
    }

}
