package Item;

import Object.Character.Enemy;

import java.awt.*;

public class Weapon extends Item {

    private int attackDamage;
    private int shotSpeed = 1;
    private int range = 15;

    private int playerPosX;
    private int playerPosY;
    private int speedDir;

    private int shotPosX;

    private int bulletSize = 5;

    public Weapon(int damage, int range, int shotSpeed){
        this.attackDamage = damage;
        this.range = range;
        this.shotSpeed = shotSpeed;
    }

    public void shoot(int speedDir, int posX, int posY){
        this.playerPosX = posX;
        this.playerPosY = posY;
        this.speedDir = speedDir;
    }

    public void moveShot(){
        //If the player is moving to the right and the range has not been reached.
        if(shotPosX < playerPosX + range && speedDir > 0){
            shotPosX =+ shotSpeed;
        }else if (shotPosX < playerPosX - range && speedDir < 0){
            shotPosX += shotSpeed;
        }
    }

    public void dealDamage(Enemy enemy){
        enemy.takeDamage(attackDamage);
    }
    public void paint(Graphics2D g){
        g.setColor(Color.YELLOW);
        g.fillRect(shotPosX,playerPosY,bulletSize,bulletSize);
    }
}
