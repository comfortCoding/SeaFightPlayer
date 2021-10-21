package rival;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.Config.*;

public class Cell {

    private final int x;
    private final int y;

    private int cellStatus;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.cellStatus = CELL_EMPTY; //CELL_EMPTY is used while initiating
    }

    public void hit(WebDriver driver, WebElement rivalField) {

        WebElement webCell = rivalField.findElement(By.xpath(".//div[@data-y='" + y + "' and @data-x='" + x + "']"));

        webCell.click();

        //read the cell status from web page and synchronize it with cellStatus field
        this.cellStatus = synchroniseCellStatusWithWeb(driver, rivalField);
    }


    public int getCellStatus() {
        return this.cellStatus;
    }


    private int synchroniseCellStatusWithWeb(WebDriver driver, WebElement rivalField) {

        WebElement webCell = rivalField.findElement(By.xpath(".//div[@data-y='" + y + "' and @data-x='" + x + "']"));
        WebElement parentCell = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].parentNode;", webCell);

        if (elementHasClass(parentCell, CELL_NAME_IS_EMPTY)) {
            cellStatus = CELL_EMPTY;
        }

        if (elementHasClass(parentCell, CELL_NAME_IS_HIT)
                && !elementHasClass(parentCell, CELL_NAME_IS_DONE)) {
            cellStatus = CELL_HIT;
        }

        if (elementHasClass(parentCell, CELL_NAME_IS_DONE)) {
            cellStatus = CELL_DONE;
        }

        if (elementHasClass(parentCell, CELL_NAME_IS_MISS)) {
            cellStatus = CELL_MISS;
        }

        return cellStatus;
    }

    private boolean elementHasClass(WebElement element, String className) {
        return element.getAttribute("class").contains(className);
    }

}
