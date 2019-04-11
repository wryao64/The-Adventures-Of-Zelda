package Object.Character;

import Object.Object;

public class Character extends Object {

    public Character(int w, int h, int x, int y){
        super(w,h,x,y);
    }

    protected int speedX, speedY;

    /*
    Horizontal movement of the character.
     */
    public void move(){
        posX = posX + speedX;
    }

    /*
    Abstract??
     */
    public void attack(){

    }

    /*
    Abstract??
     */
    public void takeDamage(){

    }

    /*
    Getters and Setters for the speed of the character.
     */
    public void setSpeedX(int dx){ speedX = dx; }

    public void setSpeedY(int dy){
        speedY = dy;
    }

    public int getSpeedX(){ return speedX; }

    public int getSpeedY(){ return speedY; }

}
