import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rival.Board;
import rival.Cell;

import java.time.Duration;

import static config.Config.*;


public class Game {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Cell[][] board;

    public Game(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
        this.board = new Board().getBoard();
    }

    public void play() {
        driver.findElement(By.className("battlefield-start-button")).click();
        algorithm();
    }

    private void algorithm() {
        for (int y = 0; y < FIELD_SIZE; y++) {

            for (int x = 0; x < FIELD_SIZE; x++) {

                WebElement rivalField = driver.findElement(By.xpath(RIVAL_BOARD));

                waitForTheOpponent(rivalField);

                int status = board[x][y].getCellStatus();

                if (status == CELL_EMPTY) {
                    board[x][y].hit(driver,
                            rivalField);
                }
            }
        }
    }

    private void finishHit(int x, int y, int direction) {

        do {
            switch (direction) {
                case GO_UP:
                    y--;
                    break;
                case GO_DOWN:
                    y++;
                    break;
                case GO_RIGHT:
                    x++;
                    break;
                case GO_LEFT:
                    x--;
                    break;
            }
            if (!isCellValid(x, y)) {
                return;
            }
            board[x][y].hit(driver, driver.findElement(By.xpath(RIVAL_BOARD)));
        } while (board[x][y].getCellStatus() == CELL_HIT);
    }

    private void waitForTheOpponent(WebElement rivalField) {
        wait.until(ExpectedConditions.attributeToBe(rivalField, "class", "battlefield battlefield__rival"));
    }

    private boolean isCellValid(int x, int y) {
        return y >= 0 && x >= 0 && x < FIELD_SIZE && y < FIELD_SIZE;
    }
}