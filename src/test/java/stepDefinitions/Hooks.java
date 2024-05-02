package stepDefinitions;

import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hooks {

    private static WebDriver driver;
    private static WebDriverWait wait5;

    @Before
    public void openBrowser() {
        if (driver == null) {
            System.out.println("Initializing WebDriver...");
            ChromeOptions options = chromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            System.out.println("WebDriver initialized successfully.");
        } else {
            System.out.println("WebDriver already initialized.");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--incognito");
        return chromeOptions;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait5() {
        if (wait5 == null) {
            wait5 = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        }
        return wait5;
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
