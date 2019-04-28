package Object.Character;

import Controller.GameState;
import Object.Item.BossWeapon;
import Object.Item.Weapon;

import java.awt.*;

public class Boss extends Enemy {
    public static final String IMAGE_LOCATION = "Resources/Assets/";

    private final int IMG_RESIZED_W = 350;
    private final int IMG_RESIZED_H = 200;

    public Boss(int w, int h, int x, int y, Weapon weapon, double shootFreq, int health) {
        super(w, h, x, y, GameState.LEVEL_BOSS, weapon, shootFreq, health);
        enemyID = "Boss";

        imageLocation = IMAGE_LOCATION + "boss.png";
        imageToPaint = loadImage();
    }

    public void shootWeapon() {
        BossWeapon weapon = (BossWeapon) this.weapon;
        weapon.shoot(posX, posY, width, height);
    }

    @Override
    public void paintObject(Graphics2D g) {
        g.drawImage(imageToPaint, (int) posX - 135, (int) posY, IMG_RESIZED_W,
                IMG_RESIZED_H, null);
        weapon.paintObject(g);
    }

}
