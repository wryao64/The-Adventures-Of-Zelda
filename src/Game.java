public class Game {
    public static void main(String[] args) {
        View.GameController gameController = new View.GameController();
        new Thread(gameController).start();
    }
}