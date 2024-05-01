package ru.ibs.steps;

import io.cucumber.java.ru.И;
import ru.ibs.managers.PageManager;
import ru.ibs.pages.Catalog;

import java.util.Map;

public class CatalogSteps {

    @И("пользователь находится на странице каталога: {string}")
    public void checkTitle(String title) {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.checkTitle(title);
    }

    @И("пользователь заполняет параметр {string}, значение min: {string}, значение max: {string}")
    public void fieldGapValue(String param, String minValue, String maxValue) {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.fieldGap(param, minValue, maxValue);
    }

    @И("пользователь заполняет параметр фильтр:")
    public void fieldGapValue(Map<String, String> dataTable) {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.fieldParam(dataTable);
    }

    @И("^пользователь проверяет, что поле Товаров на странице: \"(не отображается|по 24|по 36|по 48|по 100)\"$")
    public void checkCountProduct(String count) {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.checkCount(count);
    }

    @И("пользователь запоминает, товар с индексом: {string}")
    public void saveProduct(String number) {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.saveProduct(number);
    }

    @И("пользователь вбивает в поиск, товар который он запомнил")
    public void search() {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.searchProduct();
    }

    @И("пользователь проверяет, что в поиске отображается товар который он запомнил")
    public void searchOneProduct() {
        Catalog catalog = PageManager.getPageManager().getPage(Catalog.class);
        catalog.checkSearchOneProduct();
    }
}
