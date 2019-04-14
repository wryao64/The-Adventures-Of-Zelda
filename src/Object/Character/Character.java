package Object.Character;

import Object.Object;

public abstract class Character extends Object {

    public Character(double w, double h, double x, double y){
        super(w,h,x,y);
    }

    protected double speedX, speedY;

    /*
    Horizontal movement of the character.
     */
    public void move(){
        posX = posX + speedX;
        posY = posY + speedY;
    }

    public void attack(){ }

    public void takeDamage(){ }

    /*
    Getters and Setters for the speed of the character.
     */
    public void setSpeedX(double dx){ speedX = dx; }

    public void setSpeedY(double dy){ speedY = dy; }

    public double getSpeedX(){ return speedX; }

    public double getSpeedY(){ return speedY; }

}
