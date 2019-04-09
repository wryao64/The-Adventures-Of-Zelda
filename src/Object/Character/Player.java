
package Object.Character;


public class Player extends Character {

    public Player(int w, int h, int x, int y){
        super(w,h,x,y);

    }

    private int lives;
    private int jumpHeight;

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

    public int getLives(){
        return lives;
    }

    public void loseLife(){
        lives--;
    }


}
