package ru.ibs.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
public class DriverManager {

    private WebDriver driver;
    private static DriverManager INSTANCE = null;
    private final TestPropManager testPropManager = TestPropManager.getPropertyManager();

    private DriverManager() {
    }

    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver(testPropManager.getProperty("type.browser"));
        }
        return driver;
    }

    public WebDriver getDriver(String browser) {
        if (driver == null) {
            initDriver(browser);
        }
        return driver;
    }


    private void initDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "edge":
                System.setProperty("webdriver.edge.driver", testPropManager.getProperty("path.browser.edge"));
                EdgeOptions edgeOptions = new EdgeOptions();
                //edgeOptions.addArguments("--incognito");
                edgeOptions.addArguments("--window-size=1920,1080");
                //edgeOptions.addArguments("--remote-allow-origins");
                //edgeOptions.setCapability("acceptInsecureCerts", true);
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
                System.setProperty("webdriver.edge.driver", testPropManager.getProperty("path.browser.chrome"));
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--window-size=1920,1080");
                //chromeOptions.addArguments("--remote-allow-origins");
                //chromeOptions.setCapability("acceptInsecureCerts", true);
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new RuntimeException("Для браузера '" + driver.getTitle() + "' не заданы настройки");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //ожидания появления элемента на странице
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10)); //ожидания полной загрузки страницы
        driver.manage().window().maximize();
    }
}