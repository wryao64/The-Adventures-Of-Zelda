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

        Bullet bullet1 = new Bullet(charPosX, charPosY, - shotSpeed, -shotSpeed/2, range/2,gameState,true);
        bulletMap.put(bullet1.toString(),bullet1);

        Bullet bullet2 = new Bullet(charPosX + charWidth, charPosY, shotSpeed, -shotSpeed/2, range/2,gameState,true);
        bulletMap.put(bullet2.toString(),bullet2);

        Bullet bullet3 = new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, shotSpeed/2, range/2,gameState,true);
        bulletMap.put(bullet3.toString(),bullet3);

        Bullet bullet4 = new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed,
                shotSpeed/2, range/2,gameState,true);
        bulletMap.put(bullet4.toString(),bullet4);

        Bullet bullet5 = new Bullet(charPosX, charPosY + (charHeight/2), - shotSpeed, range,gameState,true);
        bulletMap.put(bullet5.toString(),bullet5);

        Bullet bullet6 = new Bullet(charPosX, charPosY , - shotSpeed, range,gameState,true);
        bulletMap.put(bullet6.toString(),bullet6);

        Bullet bullet7 = new Bullet(charPosX + charWidth, charPosY + (charHeight/2), shotSpeed, range,gameState,true);
        bulletMap.put(bullet7.toString(),bullet7);

        Bullet bullet8 = new Bullet(charPosX + charWidth, charPosY, shotSpeed, range,gameState,true);
        bulletMap.put(bullet8.toString(),bullet8);
    }

    public void moveShot() {
        for(String key : bulletMap.keySet()) {
            Bullet b = bulletMap.get(key);
            if (b != null)
                if ((b.getBulletPosX() > (weaponPosX - range)) && (b.getBulletPosX() < (weaponPosX + range))) {
                    b.moveShot();
                } else {
                    bulletMap.remove(b.toString());
            }
        }
    }

}
