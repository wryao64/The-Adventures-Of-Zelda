package Object.Character;

import Controller.GameState;
import Object.Item.Weapon;

public class Boss extends Enemy {
    public Boss(int w, int h, int x, int y, Weapon weapon, int shootFreq, int health) {
        super(w,h,x,y, GameState.LEVEL_BOSS, weapon,shootFreq,health);
    }

    public void shoot(){

    }

}
