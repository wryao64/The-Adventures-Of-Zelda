package Object.Character;

public class Enemy extends Character {

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
