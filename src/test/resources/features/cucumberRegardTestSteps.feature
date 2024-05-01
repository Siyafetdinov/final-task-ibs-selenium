# language: ru

@all
Функция: Сценарий РЕГАРД

  Предыстория:
    * пользователь открывает браузер "edge" и раскрывает на весь экран
    * пользователь переходит по ссылке: "https://www.regard.ru/"

  @correct
  Структура сценария: Scenario 1
    * пользователь нажал на кнопку "Каталог"
    * пользователь выбрал в верхнем меню пункт: <itemHeaderMenu>
    * пользователь находится на странице <itemHeaderMenu>, с карточками товаров
    * пользователь выбрал карточку товара: <itemCatalog>
    * пользователь находится на странице каталога: <itemCatalog>
    * пользователь заполняет параметр "Цена", значение min: "20000", значение max: ""
    * пользователь заполняет параметр фильтр:
    | Производитель | Gigabyte |
    * пользователь проверяет, что поле Товаров на странице: "по 24"
    * пользователь запоминает, товар с индексом: "2"
    * пользователь вбивает в поиск, товар который он запомнил
    * пользователь проверяет, что в поиске отображается товар который он запомнил
    Примеры:
      | itemHeaderMenu         | itemCatalog  |
      | "Комплектующие для ПК" | "Видеокарты" |


  @correct
  Структура сценария: Scenario 2
    * пользователь нажал на кнопку "Каталог"
    * пользователь выбрал в верхнем меню пункт: <itemHeaderMenu>
    * пользователь находится на странице <itemHeaderMenu>, с карточками товаров
    * пользователь выбрал карточку товара: <itemCatalog>
    * пользователь находится на странице каталога: <itemCatalog>
    * пользователь заполняет параметр "Цена", значение min: "2000", значение max: ""
    * пользователь заполняет параметр фильтр:
      | Производитель | A4Tech |
    * пользователь проверяет, что поле Товаров на странице: "не отображается"
    * пользователь запоминает, товар с индексом: "1"
    * пользователь вбивает в поиск, товар который он запомнил
    * пользователь проверяет, что в поиске отображается товар который он запомнил
    Примеры:
      | itemHeaderMenu         | itemCatalog  |
      | "Периферия" | "Клавиатуры" |


  @correct
  Структура сценария: Scenario 3 (дополнительный)
    * пользователь нажал на кнопку "Каталог"
    * пользователь выбрал в верхнем меню пункт: <itemHeaderMenu>
    * пользователь находится на странице <itemHeaderMenu>, с карточками товаров
    * пользователь выбрал карточку товара: <itemCatalog>
    * пользователь находится на странице каталога: <itemCatalog>
    * пользователь заполняет параметр "Цена", значение min: "20000", значение max: "600000"
    * пользователь заполняет параметр "Тактовая частота", значение min: "2000", значение max: ""
    * пользователь заполняет параметр фильтр:
      | Socket | AM4, AM5, LGA 1700, LGA 1200 |
      | Количество ядер | 6, 8, 14, 16 |
    * пользователь проверяет, что поле Товаров на странице: "по 24"
    * пользователь запоминает, товар с индексом: "2"
    * пользователь вбивает в поиск, товар который он запомнил
    * пользователь проверяет, что в поиске отображается товар который он запомнил
    Примеры:
      | itemHeaderMenu         | itemCatalog  |
      | "Комплектующие для ПК" | "Процессоры" |