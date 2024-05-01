package ru.ibs.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[text()='Каталог']//..")
    WebElement catalogBtn;

    @FindBy(xpath = "//div[contains(@class,'CatalogPanel_wrap')]")
    WebElement catalogPanel;

    public void openMenu() {
        catalogBtn.click();
        Assertions.assertTrue(catalogPanel.getAttribute("class").contains("rg-anim-fade-end"),
                "Меню каталога не открылось");
    }

    public void clickItemHeaderMenu(String itemMenu) {
        driver.findElement(By.xpath(
                String.format("//li[contains(@class,'Catalog_mainCategory')]//div[text()='%s']", itemMenu)
        )).click();
    }


}
