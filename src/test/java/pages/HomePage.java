package pages;

import constants.Urls;
import components.Header;

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