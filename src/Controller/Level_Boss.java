package Controller;

import Object.Platform;

public class Level_Boss extends Level {

    public Level_Boss(){

        //Set the player
        this.player = player;

        //Create the platforms
        platforms.add(new Platform(100,30,350,410));
        platforms.add(new Platform(120,30,200,360));
        platforms.add(new Platform(100,30,120,300));
        platforms.add(new Platform(100,30,260,250));

        //Create the puzzle chest
        //Create the enemies
    }
}
