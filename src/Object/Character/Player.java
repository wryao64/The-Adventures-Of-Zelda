
package Object.Character;


public class Player extends Character {

    private int lives;


    public int getLives(){
        return lives;
    }

    public void loseLife(){
        lives--;
    }

}
