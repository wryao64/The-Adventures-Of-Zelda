package Controller;

import Object.Character.Player;
import Object.Platform;

import java.util.ArrayList;

public class Level_Tutorial extends Level{

    public Level_Tutorial(){

        //Set the player
        player = new Player(30,30,400,50);

        //Create the platforms
        platforms = new ArrayList<Platform>();
        platforms.add(new Platform(100,30,350,410));
        platforms.add(new Platform(120,30,200,360));
        platforms.add(new Platform(100,30,120,300));
        platforms.add(new Platform(100,30,260,250));

        //Create the puzzle chest
        //Create the enemies
    }
}
