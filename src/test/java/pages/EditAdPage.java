package pages;

import com.codeborne.selenide.SelenideElement;
import constants.Urls;
import components.Header;
import lombok.Getter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Getter
public class EditAdPage {

    private final SelenideElement titleLabel = $(".createListing_title__IFtFs");

    private final Header header;

    public EditAdPage() {
        this.header = new Header();
    }

    public EditAdPage openPage() {
        open(Urls.EDIT_AD_PAGE_URL);
        return this;
    }

    public EditAdPage shouldHaveTitle(String expectedTitle) {
        titleLabel.shouldBe(visible).shouldHave(text(expectedTitle));
        return this;
    }
}