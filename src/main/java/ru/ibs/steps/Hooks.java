package ru.ibs.steps;

import io.cucumber.java.AfterAll;

import io.cucumber.java.ru.Допустим;
import org.openqa.selenium.WebDriver;
import ru.ibs.managers.DriverManager;

public class Hooks {
    private static DriverManager driverManager = DriverManager.getDriverManager();
    private static WebDriver driver;

    @Допустим("^пользователь открывает браузер \"(edge|chrome)\" и раскрывает на весь экран$")
    public void openBrowser(String browser) {
        driver = driverManager.getDriver(browser);
    }

    @Допустим("пользователь переходит по ссылке: {string}")
    public void openPage(String url) {
        driver.get(url);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
        driver = null;
    }
}
