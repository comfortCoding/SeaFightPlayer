import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static config.Config.WEB_DRIVER_CHROME;
import static config.Config.WEB_DRIVER_CHROME_SETTINGS;

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
        System.setProperty(WEB_DRIVER_CHROME_SETTINGS, WEB_DRIVER_CHROME);
        driver = new ChromeDriver();
    }
}
