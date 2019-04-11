package Object.Character;

public class Enemy extends Character {

    public Enemy(int w, int h, int x, int y){
        super(w,h,x,y);
    }

    private int health;
    public void attack(){

    }

    public void takeDamage(int damage){
        health = health-damage;
        if(health <= 0){
            //die
        }
    }
}
