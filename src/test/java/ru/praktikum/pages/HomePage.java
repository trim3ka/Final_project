package ru.praktikum.pages;

import ru.praktikum.constants.Urls;
import ru.praktikum.components.Header;

import static com.codeborne.selenide.Selenide.open;

public class HomePage {

    private Header header;

    public HomePage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public HomePage openPage() {
        open(Urls.HOME_PAGE_URL);
        return this;
    }
}