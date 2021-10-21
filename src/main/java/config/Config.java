package config;

public class Config {
    public static final String DOMAIN = "http://ru.battleship-game.org/";
    public static final String DOMAIN_DIRECT_GAME = "http://ru.battleship-game.org/id23022155";

    public static final String CELL_NAME_IS_EMPTY = "empty";
    public static final String CELL_NAME_IS_HIT = "hit";
    public static final String CELL_NAME_IS_DONE = "done";
    public static final String CELL_NAME_IS_MISS = "miss";

    public static final String RIVAL_BOARD = "//div[contains(@class, 'battlefield__rival')]";
    public static final String WEB_START_BUTTON = "battlefield-start-button";
    public static final String WEB_RIVAL_BOARD_NAME = "battlefield battlefield__rival";
    public static final String WEB_CLASS_ATT = "class";
    public static final String WEB_DRIVER_CHROME_SETTINGS = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_CHROME = "lib\\chromedriver.exe";


    public static final int FIELD_SIZE = 10;
    public static final int TIME_OUT = 30;

    public static final int CELL_EMPTY = 0;
    public static final int CELL_MISS = -1;
    public static final int CELL_HIT = 1;
    public static final int CELL_DONE = 2;

}
