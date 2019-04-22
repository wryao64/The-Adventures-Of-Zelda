package Object.Character;

import Controller.GameState;

public class Boss extends Enemy {

    public Boss(int w, int h, int x, int y){
        super(w,h,x,y, GameState.LEVEL_BOSS);
    }

    public void attack(){

    }

}
