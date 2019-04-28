package Object.Item;

public class BossWeapon extends Weapon {
    public static final String IMAGE_LOCATION = "Resources/Assets/";

    public BossWeapon(int damage, int range, int shotSpeed) {
        super(damage,range,shotSpeed,true);
        imageLocation =  IMAGE_LOCATION + "bulletBoss.png";
        image = loadImage();
    }

    public void shoot(double charPosX, double charPosY, double charWidth, double charHeight) {
        weaponPosX = charPosX + charWidth/2;
        weaponPosY = charPosY + charHeight/2;

        bullets.add(new Bullet(charPosX, charPosY, - shotSpeed, -shotSpeed/2, range/2,gameState,true));
        bullets.add(new Bullet(charPosX + charWidth, charPosY, shotSpeed, -shotSpeed/2, range/2,gameState,true));

        bullets.add(new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, shotSpeed/2, range/2,gameState,true));
        bullets.add(new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed,
                shotSpeed/2, range/2,gameState,true));

        bullets.add(new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, range,gameState,true));
        bullets.add(new Bullet(charPosX, charPosY , - shotSpeed, range,gameState,true));
        bullets.add(new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed, range,gameState,true));
        bullets.add(new Bullet(charPosX + charWidth, charPosY, shotSpeed, range,gameState,true));
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
