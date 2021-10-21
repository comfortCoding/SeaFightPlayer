package config;

public class Config {
    public static final String DOMAIN = "http://ru.battleship-game.org/";
    public static final String DOMAIN_DIRECT_GAME = "http://ru.battleship-game.org/id23022155";

    public static final String CELL_NAME_IS_EMPTY = "empty";
    public static final String CELL_NAME_IS_HIT = "hit";
    public static final String CELL_NAME_IS_DONE = "done";
    public static final String CELL_NAME_IS_MISS = "miss";

    public static final String RIVAL_BOARD = "//div[contains(@class, 'battlefield__rival')]";

    public static final int FIELD_SIZE = 10;
    public static final int TIME_OUT = 30;

    public static final int ERROR_CODE = -100;

    public static final int CELL_EMPTY = 0;
    public static final int CELL_MISS = -1;
    public static final int CELL_HIT = 1;
    public static final int CELL_DONE = 2;

    public static final int GO_UP = 1;
    public static final int GO_DOWN = 2;
    public static final int GO_LEFT = 3;
    public static final int GO_RIGHT = 4;

}
