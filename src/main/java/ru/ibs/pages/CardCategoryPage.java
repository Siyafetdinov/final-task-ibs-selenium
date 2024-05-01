package ru.ibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardCategoryPage extends BasePage {
    @FindBy(xpath = "//h1[contains(@class,'PageTitle_title')]")
    private WebElement title;


    public void clickCardCategory(String nameCard) {
        WebElement element = driver.findElement(By.xpath(
                String.format("//a[contains(@class,'CardCategory_wrap')]//p[text()='%s']", nameCard)
        ));
        element.click();
        waitUntilElementToBeInvisible(element);
    }

    public void checkTitle(String extendsText) {
        super.checkTitle(title, extendsText);
    }
}
