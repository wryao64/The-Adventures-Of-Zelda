
package Object.Character;


import Item.Weapon;

import java.awt.*;

public class Player extends Character {

    private int lives;
    private final int jumpHeight = 7;

    private int startPosX;
    private int startPosY;

    private Weapon weapon;

    public Player(int w, int h, int x, int y){
        super(w,h,x,y);
    }

    public Player(int w, int h, int x, int y, int startX, int startY){
        super(w,h,x,y);
        startPosX = startX;
        startPosY = startY;
    }

    /*
    Basic actions for the player.
     */
    public void jump(){
        posY = posY + jumpHeight;
    }

    public void fall(int gravity, int maxSpeedY){

        if(speedY <  maxSpeedY) {
            speedY += gravity;
        }else{
            speedY = maxSpeedY;
        }
    }

    /*
    public void shootWeapon(int speedDir){
        weapon.shoot(speedDir);
    }*/

    /*
    Resets the player back to the starting position in the level ie when they die
     */
    public void initPosition(){
        posX = startPosX;
        posY = startPosY;
    }

    @Override
    public void paintObject(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(posX,posY,width,height);
    }

    public int getLives(){
        return lives;
    }

    public void loseLife(){
        lives--;
    }



}
