package Object.Character;

import Controller.GameState;
import Object.Item.BossWeapon;
import Object.Item.Weapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Boss extends Enemy {
    private final int IMAGE_WIDTH = 18;
    private final int IMAGE_HEIGHT = 11;
    private final int IMG_RESIZED_W = 55;
    private final int IMG_RESIZED_H = 40;

    public Boss(int w, int h, int x, int y, Weapon weapon, double shootFreq, int health) {
        super(w,h,x,y, GameState.LEVEL_BOSS, weapon,shootFreq,health);
        enemyID = "Boss";
    }

    public void shootWeapon(){
        BossWeapon weapon = (BossWeapon) this.weapon;
        weapon.shoot(posX,posY,width,height);
    }

    @Override
    public void paintObject(Graphics2D g) {
        g.fill(new Rectangle2D.Double(posX, posY, width, height));
        weapon.paintObject(g);
    }

}
