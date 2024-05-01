package ru.ibs.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.managers.DriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BasePage {
    protected DriverManager driverManager = DriverManager.getDriverManager();
    protected WebDriver driver = driverManager.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver,
            Duration.ofSeconds(20, 1000));

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    protected Boolean waitUntilElementToBeInvisible(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void checkTitle(WebElement element, String expectedTitle){
        waitUntilElementToBeVisible(element);
        assertEquals(expectedTitle, element.getText(),
                        "Текст заголовка не соответствует ожидаемому");
    }

    protected void upPage() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollTo(0, 0)");
    }


}
