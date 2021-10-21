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
        driver.findElement(By.className(WEB_START_BUTTON)).click();
        algorithm();
    }

    private void algorithm() {
        for (int y = 0; y < FIELD_SIZE; y++) {

            for (int x = 0; x < FIELD_SIZE; x++) {
                // we need to find the rival field first
                WebElement rivalField = driver.findElement(By.xpath(RIVAL_BOARD));
                //then we need to apply waiting to the algorithm till the rival field becomes available
                //(classname changes to WEB_RIVAL_BOARD_NAME)
                waitForTheOpponent(rivalField);

                int status = board[x][y].getCellStatus();

                if (status == CELL_EMPTY) {
                    board[x][y].hit(driver,
                            rivalField);
                }
            }
        }
    }

    private void waitForTheOpponent(WebElement rivalField) {
        wait.until(ExpectedConditions.attributeToBe(rivalField, WEB_CLASS_ATT, WEB_RIVAL_BOARD_NAME));
    }

}