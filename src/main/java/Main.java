public class Main {
    private static final String DOMAIN = "http://ru.battleship-game.org/";
    private static final String DOMAIN_DIRECT_GAME = "http://ru.battleship-game.org/id23022155";

    

    public static void main(String[] args) {
        BrowserDriver game = new BrowserDriver(DOMAIN);
        game.run();
    }
}
