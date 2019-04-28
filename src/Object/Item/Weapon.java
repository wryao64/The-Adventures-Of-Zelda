package Object.Item;

import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

import Controller.GameState;
import Object.Object;

public class Weapon extends Object {
    protected int attackDamage;
    protected int shotSpeed;
    protected int range;

    protected double bulletDir;
    protected double bulletSize = 12;
    protected double weaponPosX;
    protected double weaponPosY;

    protected double maxBullets = 1;

    protected ConcurrentHashMap<String, Bullet> bulletMap = new ConcurrentHashMap<String, Bullet>();

    protected GameState gameState = GameState.TUTORIAL;
    protected Boolean enemy;

    public Weapon(int damage, int range, int shotSpeed, boolean enemy) {
        this.attackDamage = damage;
        this.range = range;
        this.shotSpeed = shotSpeed;
        this.enemy = enemy;
    }

    public void shoot(double posX, double posY, double dir) {
        if (bulletMap.size() < maxBullets) {
            //Sets the direction to shoot as either to the right or to the left depending on which direction the player is
            //facing.
            this.bulletDir = dir;

            //Set the x start position of the weapon's shots depending on which direction the player is facing.
            if (bulletDir > 0) {
                this.weaponPosX = posX + 50;
            } else {
                this.weaponPosX = posX - bulletSize;
            }

            //Initialise the current and start y position of the player, these will not change.
            this.weaponPosY = posY + 20;

            Bullet bullet = new Bullet(weaponPosX, weaponPosY, shotSpeed * bulletDir, range, gameState, enemy);
            bulletMap.put(bullet.toString(),bullet);
        }
    }

    /**
     * Moving the bullet fired from the weapon. Increments in the direction where it was shot while the bullet is still
     * in the range of the weapon.
     */
    public void moveShot() {
        for(String key : bulletMap.keySet()) {
            Bullet b = bulletMap.get(key);
            if ((b.getBulletPosX() > (weaponPosX - range)) && (b.getBulletPosX() < (weaponPosX + range))) {
                b.moveShot();
            } else {
                bulletMap.remove(b.toString());
            }
        }
    }

    /**
     * Paint the bullet fired by the gun.
     */
    public void paintObject(Graphics2D g) {
        for(String key : bulletMap.keySet()) {
            Bullet b = bulletMap.get(key);
            b.paintObject(g);
        }
    }

    public void changeImage(GameState gameState) {
        this.gameState = gameState;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setMaxBullets(int maxBullets) {
        this.maxBullets = maxBullets;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ConcurrentHashMap<String,Bullet> getBullets() {
        return bulletMap;
    }
}
