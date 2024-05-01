package ru.ibs.steps;

import io.cucumber.java.ru.И;
import ru.ibs.managers.PageManager;
import ru.ibs.pages.HomePage;

public class HomeSteps {

    @И("пользователь нажал на кнопку \"Каталог\"")
    public void openCatalog(){
        HomePage homePage = PageManager.getPageManager().getPage(HomePage.class);
        homePage.openMenu();
    }

    @И("пользователь выбрал в верхнем меню пункт: {string}")
    public void selectItem(String item){
        HomePage homePage = PageManager.getPageManager().getPage(HomePage.class);
        homePage.clickItemHeaderMenu(item);
    }
}
