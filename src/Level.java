
import Object.Platform;
import Object.Character.Enemy;
import Object.Character.Player;
import Item.Puzzle;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
Class that handles the co-ordination between the player, the enemies on the screen and the platforms on the screen. Handles
the logistics of the level like the current score, which enemies are killed and the number of lives of the player.
 */
public class Level {

    BufferedImage background;



    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Platform> platforms;
    private Puzzle puzzle;

    /*
    Method for painting the current level i.e backgrounds,players, enemies and platforms.
     */
    public void paintLevel(){

    }

}
