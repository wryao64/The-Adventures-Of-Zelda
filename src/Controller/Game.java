package Controller;

public class Game {
    public static GameController gameController = new GameController();

    public static void main(String[] args) {
        new Thread(gameController).start();
    }
}