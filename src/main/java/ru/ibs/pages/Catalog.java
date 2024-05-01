package ru.ibs.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.stored.DataProductName;

import java.util.*;

public class Catalog extends BasePage {
    @FindBy(xpath = "//h1[contains(@class,'PageTitle_title')]")
    private WebElement title;

    @FindBy(xpath = "//div[text()='Товаров на странице: ']//span")
    private WebElement countElement;
    @FindBy(xpath = "//input[@aria-label=\"Поиск\"]")
    private WebElement search;

    @FindBy(xpath = "//div[contains(@class,'sticky-outer-wrapper')]//..")
    private WebElement divLeftMenu;
    private DataProductName tmp = new DataProductName();

    /**
     * Заполняем параметр, у которого есть поле Min и Max
     *
     * @param param наименование параметра
     * @param minValue минимальное значение, если вводить не нужно, то оставить пустым
     * @param maxValue максимальное значение, если вводить не нужно, то оставить пустым
     */
    public void fieldGap(String param, String minValue, String maxValue) {
        checkHiddenParam(param);

        if (!minValue.isEmpty()) {
            super.upPage();
            driver.findElement(By.xpath(String.format(
                    "//span[text()='%s']//ancestor::section//input[@name='min']", param
            ))).sendKeys(minValue);
            waitPageLoader(2000, 350);
        }

        if (!maxValue.isEmpty()) {
            super.upPage();
            driver.findElement(By.xpath(String.format(
                    "//span[text()='%s']//ancestor::section//input[@name='max']", param
            ))).sendKeys(maxValue);
            waitPageLoader(2000, 350);
        }
    }

    /**
     * Проверка поля "Товаров на странице"
     * На сайте регард, если товаров > 24. То это поле не отображается. В данном случае
     * передавать значение "не отображается"
     *
     * @param count сколько отображается (не отображается, по 24, по 36, по 48, по 100)
     */
    public void checkCount(String count) {
        if (count.equals("не отображается")) {
            String pageSource = driver.getPageSource();
            Assertions.assertFalse(pageSource.contains("Товаров на странице: "),
                    "Размерность выданных товаров отображается");
        } else {
            Assertions.assertEquals(count, countElement.getText(), "Размерность не совпадает");
        }
    }

    /**
     * Заполняем параметр, в котором фильтрация через чек-боксы
     * При необходимости выбрать несколько значений, то они передаются через <b>", "<b/>
     *
     * @param dataTable параметры
     */
    public void fieldParam(Map<String, String> dataTable) {
        for (Map.Entry<String, String> entry : dataTable.entrySet()) {
            checkHiddenParam(entry.getKey());
            checkListShowMore(entry.getKey());

            for (String item : entry.getValue().split(", ")) {
                WebElement element = driver.findElement(By.xpath(String.format(
                        "//span[text()='%s']//ancestor::section//label[(text()='%s')]//..//..//label", entry.getKey(), item)));
                super.upPage();
                element.click();
                waitPageLoader(2000, 350);
            }
        }
    }

    public void checkTitle(String extendsText) {
        super.checkTitle(title, extendsText);
    }

    public String getProduct(String number) {
        return driver.findElement(By.xpath(String.format(
                        "//div[contains(@class, 'Card_listing')][%s]//div[contains(@class, 'CardText_title')]", number)))
                .getAttribute("title");
    }

    public void saveProduct(String number) {
        tmp.setTempValue(getProduct(number));
    }

    public void searchProduct() {
        search.sendKeys(tmp.getTempValue());
        WebElement searchElement = driver.findElement(By.xpath("//div[contains(@class,'Search_resultsContainer')]"));
        search.sendKeys(Keys.ENTER);
        super.waitUntilElementToBeInvisible(searchElement);
        waitPageLoader(10000, 250);
    }

    public void checkSearchOneProduct() {
        List<WebElement> elementsProduct = driver.findElements(By.xpath(
                "//div[contains(@class, 'Card_listing')]//div[contains(@class, 'CardText_title')]"));
        if (elementsProduct.size() > 1) {
            Assertions.fail("Товаров нашлось больше 1");
        }

        Assertions.assertEquals(tmp.getTempValue(), getProduct("1"), "Товары не совпали");
    }

    /**
     * Проверяем, что параметр не свернут. Если свернут, то раскрываем
     *
     * @param param наименования параметра который проверяем
     */
    private void checkHiddenParam(String param) {
        boolean hidden = Boolean.parseBoolean(driver.findElement(By.xpath(String.format(
                "//span[text()='%s']//ancestor::section//div[@aria-hidden]", param))).getAttribute("aria-hidden"));

        if (hidden) {
            driver.findElement(By.xpath(String.format(
                    "//span[text()='%s']//ancestor::section", param))).click();

            // Ожидаем раскрытия списка
            super.waitUntilElementToBeVisible(driverManager.getDriver().findElement(By.xpath(String.format(
                    "//span[text()='%s']//ancestor::section//div[@aria-hidden='false']", param
            ))));
        }
    }

    /**
     * Проверяем, что у параметра развернуты все значения. Если свернуты, то раскрываем
     *
     * @param param наименования параметра который проверяем
     */
    private void checkListShowMore(String param) {
        WebElement lastElementList = driver.findElement(By.xpath(String.format(
                "//span[text()='%s']//ancestor::section//ul//li[last()]", param
        )));

        if (lastElementList.getAttribute("class").contains("_showMore")) {
            lastElementList.click();
            waitShowMore(driver.findElement(By.xpath(String.format(
                    "//span[text()='%s']//ancestor::section//ul//li[last()]", param
            ))));
        }
    }

    /**
     * Метод ожидания результата страницы каталога
     * Метод сначала проверяет PageSource, на атрибуты которые отображаются при загрузке товаров
     * И потом проверяет, что товар (title) загружен и отображается
     *
     * @param maxWaitMillis максимальное ожидание
     * @param pollDelimiter промежуток через который будет проверяться загрузка
     */
    private void waitPageLoader(int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        String prevState;
        String elementProduct;
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            prevState = driver.getPageSource();
            try {
                Thread.sleep(pollDelimiter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!prevState.contains("PageTitle_countSkeleton")) {
                try {
                    elementProduct = driver.findElement(By.xpath(
                                    "//div[contains(@class, 'Card_listing')]//div[contains(@class, 'CardText_title')]"))
                            .getAttribute("class");

                    if (!elementProduct.contains("CardText_notAllowed")) {
                        break;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
        }
    }

    /**
     * Метод ожидания, пока весь внутренний список раскроется
     *
     * @param element последний элемент в списке параметра
     */
    private void waitShowMore(WebElement element) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + 2000) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!element.getAttribute("class").contains("_showMore")) {
                break;
            }
        }
    }
}
