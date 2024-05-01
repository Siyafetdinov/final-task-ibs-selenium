package ru.ibs.steps;

import io.cucumber.java.ru.И;
import ru.ibs.managers.PageManager;
import ru.ibs.pages.CardCategoryPage;

public class CardCategorySteps {
    @И("пользователь выбрал карточку товара: {string}")
    public void selectCategory(String nameCard){
        CardCategoryPage cardCategoryPage = PageManager.getPageManager().getPage(CardCategoryPage.class);
        cardCategoryPage.clickCardCategory(nameCard);
    }

    @И("пользователь находится на странице {string}, с карточками товаров")
    public void checkTitle(String title) {
        CardCategoryPage cardCategoryPage = PageManager.getPageManager().getPage(CardCategoryPage.class);
        cardCategoryPage.checkTitle(title);
    }
}
