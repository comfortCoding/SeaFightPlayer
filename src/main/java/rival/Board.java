package rival;

import static config.Config.FIELD_SIZE;

public class Board {

    private final Cell[][] board;

    public Board() {

        this.board = new Cell[FIELD_SIZE][FIELD_SIZE];

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                this.board[x][y] = new Cell(x, y);
            }
        }
    }

    public Cell[][] getBoard() {
        return this.board;
    }
}
