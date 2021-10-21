import static config.Config.DOMAIN;
import static config.Config.DOMAIN_DIRECT_GAME;

public class Main {
    public static void main(String[] args) {
        BrowserDriver game = new BrowserDriver(DOMAIN);
        game.run();
    }
}
