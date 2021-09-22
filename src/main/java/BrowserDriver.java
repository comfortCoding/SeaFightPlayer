import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriver {

    private WebDriver driver;

    public BrowserDriver(String url) {
        setPropertiesChrome();

        driver.manage().window().maximize();
        driver.get(url);
    }

    public void run() {
        Game game = new Game(driver);
        try {
            game.play();
        } catch (WebDriverException exception) {
            driver.quit();
        }
    }

    private void setPropertiesChrome() {
        System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
        driver = new ChromeDriver();
    }
}
