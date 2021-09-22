import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.Config.*;


public class Algorithm {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public Algorithm(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
    }

    public void run() {
        Integer[] directions = {GO_LEFT, GO_RIGHT, GO_DOWN, GO_UP};

        for (int y = 0; y < FIELD_SIZE; y++) {

            for (int x = 0; x < FIELD_SIZE; x++) {

                for (Integer direction : directions) {

                    int status = getStatus(x, y);
                    if (status == CELL_EMPTY) {
                        hit(x, y);
                    } else if (status == CELL_HIT) {
                        hit(x, y, direction);
                    }
                }
            }
        }
    }

    private void hit(int x, int y) {

        if (!isCellValid(x, y)) {
            return;
        }

        WebElement rivalField = driver.findElement(By.xpath("//div[contains(@class, 'battlefield__rival')]"));

        waitForTheOpponent(rivalField);

        WebElement hitCell = rivalField.findElement(By.xpath(".//div[@data-y='" + y + "' and @data-x='" + x + "']"));
        hitCell.click();
    }

    private void hit(int x, int y, int direction) {

        int cellStatus = CELL_HIT;

        while (cellStatus == CELL_HIT) {
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
            hit(x, y);
            cellStatus = getStatus(x, y);
        }
    }

    private int getStatus(int x, int y) {

        if (!isCellValid(x, y)) {
            return ERROR_CODE;
        }

        WebElement rivalField = driver.findElement(By.xpath("//div[contains(@class, 'battlefield__rival')]"));

        waitForTheOpponent(rivalField);

        WebElement hitCell = rivalField.findElement(By.xpath(".//div[@data-y='" + y + "' and @data-x='" + x + "']"));
        WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", hitCell);

        if (elementHasClass(parent, "empty")) {
            return CELL_EMPTY;
        }

        if (elementHasClass(parent, "hit")
                && !elementHasClass(parent, "done")) {
            return CELL_HIT;
        }

        if (elementHasClass(parent, "done")) {
            return CELL_DONE;
        }

        if (elementHasClass(parent, "miss")) {
            return CELL_MISS;
        }

        return ERROR_CODE;
    }

    private boolean elementHasClass(WebElement element, String className) {
        return element.getAttribute("class").contains(className);
    }

    private void waitForTheOpponent(WebElement rivalField) {
        wait.until(ExpectedConditions.attributeToBe(rivalField, "class", "battlefield battlefield__rival"));
    }

    private boolean isCellValid(int x, int y) {
        return y >= 0 && x >= 0 && x < FIELD_SIZE && y < FIELD_SIZE;
    }
}