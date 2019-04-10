import View.Game;

public class GameController {
    public static void main(String[] args) {
        Game game = new Game();
        new Thread(game).start();
    }
}