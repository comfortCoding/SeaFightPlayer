import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriver {
    private WebDriver driver;

    private WebElement newGame;

    public BrowserDriver(String url) {
        setPropertiesChrome();

        driver.manage().window().maximize();
        driver.get(url);

        joinGame();
    }

    public void play() {
        Algorithm game = new Algorithm(driver);
        try {
            game.run();
        } catch (WebDriverException exception) {
            driver.quit();
        }
    }

    private void joinGame() {
        newGame = driver.findElement(By.className("battlefield-start-button"));
        newGame.click();
    }

    private void setPropertiesChrome() {
        System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
        driver = new ChromeDriver();
    }
}
