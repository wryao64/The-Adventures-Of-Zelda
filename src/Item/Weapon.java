package Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Weapon extends Item {

    private int attackDamage;
    private int shotSpeed;
    private int range;

    private double shotPosX;
    private double shotPosY;
    private double startPosX;
    private double startPosY;
    private double shotDir;

    private int bulletSize = 12;
    private boolean weaponShot = false;

    public Weapon(int damage, int range, int shotSpeed){
        this.attackDamage = damage;
        this.range = range;
        this.shotSpeed = shotSpeed;
    }

    public void shoot(double shotDir, double posX, double posY) {

        if(!weaponShot) {
            //Sets the direction to shoot as either to the right or to the left depending on which direction the player is
            //facing.
            this.shotDir = shotDir;

            //Set the x start position of the weapon's shots depending on which direction the player is facing.
            if (shotDir > 0) {
                this.startPosX = posX + 50;
            } else {
                this.startPosX = posX - bulletSize;
            }

            //Initialise the current x position of the weapon's shots as the start position of the weapon.
            this.shotPosX = startPosX;

            //Initialise the current and start y position of the player, these will not change.
            this.startPosY = posY + 20;
            this.shotPosY = startPosY;
            weaponShot = true;
        }
    }

    /**
     * Moving the bullet fired from the weapon. Increments in the direction where it was shot while the bullet is still
     * in the range of the weapon.
     */
    public void moveShot() {
        if(weaponShot == true) {
            if((shotPosX > (startPosX - range))&& shotDir < 0 || (shotPosX < (startPosX + range)) && shotDir >0) {
                shotPosX = shotPosX + shotSpeed * shotDir;
            }else{
                //Once the bullet leaves the maximum range, stop it's movement.
                weaponShot = false;
            }
        }
    }

    /**
     *Returns shape that spans the whole bounding box of the object.
     */
    public Rectangle2D getBounds(){
        return new Rectangle2D.Double(shotPosX,shotPosY,bulletSize,bulletSize);
    }

    public void setWeaponShot(boolean shot){ weaponShot = shot; }

    public int getAttackDamage(){
        return attackDamage;
    }

    /**
     * Paint the bullet fired by the gun.
     */
    public void paint(Graphics2D g) {
        //Only paint the bullets if they are within the range of the player and if the weapon is shot.
        if(weaponShot) {
            g.setColor(Color.GRAY);
            g.fill(new Rectangle2D.Double(shotPosX, shotPosY, bulletSize, bulletSize));
        }
    }
}
