package Object.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Weapon extends Object {

    private int attackDamage;
    private int shotSpeed;
    private int range;

    private double bulletDir;
    private double bulletSize = 12;
    private double weaponPosX;
    private double weaponPosY;

    private double maxBullets = 1;

    private ArrayList<Bullet> bullets = new ArrayList<>();

    public Weapon(int damage, int range, int shotSpeed){
        this.attackDamage = damage;
        this.range = range;
        this.shotSpeed = shotSpeed;
    }

    public void shoot(double posX, double posY,double dir) {
        if(bullets.size() < maxBullets) {
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

            Bullet bullet = new Bullet(weaponPosX, weaponPosY, shotSpeed * bulletDir, range);
            bullets.add(bullet);
        }
    }

    /**
     * Moving the bullet fired from the weapon. Increments in the direction where it was shot while the bullet is still
     * in the range of the weapon.
     */
    public void moveShot() {
        Bullet bulletToRemove = null;
      for(Bullet b : bullets) {
          //System.out.println("Range " + (weaponPosX-range) + "-" + (weaponPosX+range));

          if ((b.getBulletPosX() > (weaponPosX - range)) && (b.getBulletPosX() < (weaponPosX + range))) {
              //System.out.println("Shot in range");
              b.moveShot();
          } else {
              //System.out.println("Shot removed");
              bulletToRemove = b;
          }
      }
      if(bulletToRemove != null) {
          bullets.remove(bulletToRemove);
      }
    }

    /**
     * Paint the bullet fired by the gun.
     */
    public void paintObject(Graphics2D g) {
        for(Bullet b : bullets){
            b.paintObject(g);
        }

    }

    public int getAttackDamage(){ return attackDamage; }

    public void setMaxBullets(int maxBullets) { this.maxBullets = maxBullets; }

    public void setBulletSize(int size) { bulletSize = size; }

    public ArrayList<Bullet> getBullets() { return bullets; }

    public void removeBullet(Bullet b) { bullets.remove(b); }
}
