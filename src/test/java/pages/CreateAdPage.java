package pages;

import com.codeborne.selenide.SelenideElement;
import api.model.CreateAdRequest;
import constants.Urls;
import components.Header;

import static com.codeborne.selenide.Selenide.*;

public class CreateAdPage {

    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement descriptionText = $("textarea[name='description']");
    private final SelenideElement priceInput = $("input[name='price']");
    private final SelenideElement publishButton = $x("//button[text()='Опубликовать']");

    private Header header;

    public CreateAdPage() {
        this.header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public CreateAdPage openPage() {
        open(Urls.CREATE_AD_PAGE_URL);
        return this;
    }

    public void setName(String name) {
        nameInput.setValue(name);
    }

    public void setDescription(String description) {
        descriptionText.setValue(description);
    }

    public void setPrice(int price) {
        priceInput.setValue(String.valueOf(price));
    }

    public void clickPublishButton() {
        publishButton.click();
    }

    public HomePage createAd(CreateAdRequest ad) {
        setName(ad.getName());
        setDescription(ad.getDescription());
        setPrice(ad.getPrice());
        clickPublishButton();
        HomePage homepage = page(HomePage.class);
        return homepage;
    }
}