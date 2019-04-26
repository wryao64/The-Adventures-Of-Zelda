package Object.Item;

public class BossWeapon extends Weapon {

    public BossWeapon(int damage, int range, int shotSpeed) {
        super(damage,range,shotSpeed);
    }

    public void shoot (double charPosX, double charPosY, double charWidth, double charHeight) {
        weaponPosX = charPosX + charWidth/2;
        weaponPosY = charPosY + charHeight/2;

        bullets.add(new Bullet(charPosX, charPosY, - shotSpeed, -shotSpeed/2, range/2));
        bullets.add(new Bullet(charPosX + charWidth, charPosY, shotSpeed, -shotSpeed/2, range/2));

        bullets.add(new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, shotSpeed/2, range/2));
        bullets.add(new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed,
                shotSpeed/2, range/2));

        bullets.add(new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, range));
        bullets.add(new Bullet(charPosX, charPosY , - shotSpeed, range));
        bullets.add(new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed, range));
        bullets.add(new Bullet(charPosX + charWidth, charPosY, shotSpeed, range));
    }

    public void moveShot() {
        Bullet bulletToRemove = null;
        for(Bullet b : bullets) {
            if ((b.getBulletPosX() > (weaponPosX - range)) && (b.getBulletPosX() < (weaponPosX + range))) {
                b.moveShot();
            } else {
                bulletToRemove = b;
            }
        } if(bulletToRemove != null) {
            bullets.remove(bulletToRemove);
        }
    }

}
