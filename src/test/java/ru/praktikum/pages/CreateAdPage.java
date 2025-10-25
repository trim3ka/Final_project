package ru.praktikum.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.praktikum.api.model.CreateAdRequest;
import ru.praktikum.constants.Urls;
import ru.praktikum.components.Header;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CreateAdPage {

    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement descriptionText = $("textarea[name='description']");
    private final SelenideElement priceInput = $("input[name='price']");
    private final SelenideElement publishButton = $x("//button[text()='Опубликовать']");

    private final SelenideElement categoryDropdownButton = $("input[name='category'] + button");
    private final SelenideElement newConditionRadioCircle = $x("//input[@value='Новый']/following-sibling::div[contains(@class, 'radioUnput_inputActive__eC-HY')]");
    private final SelenideElement usedConditionRadioCircle = $x("//input[@value='Б/У']/following-sibling::div[contains(@class, 'radioUnput_inputRegular__FbVbr')]");
    private final SelenideElement cityDropdownButton = $("input[name='city'] + button");

    private final ElementsCollection dropdownOptions = $$(".dropDownMenu_btn__o8ARs");

    private final SelenideElement successMessage = $(".alert-success");

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

    public void selectCategory(String category) {
        categoryDropdownButton.click();
        Selenide.sleep(1000);
        dropdownOptions.findBy(text(category)).click();
    }

    public void selectCondition(String condition) {
        if ("Б/У".equals(condition)) {
            usedConditionRadioCircle.shouldBe(visible).click();
        }
    }

    public void selectCity(String city) {
        cityDropdownButton.click();
        Selenide.sleep(1000);
        dropdownOptions.findBy(text(city)).click();
    }

    public CreateAdPage shouldHaveSuccessMessage() {
        successMessage.shouldBe(visible);
        return this;
    }

    public HomePage createAd(CreateAdRequest ad) {
        setName(ad.getName());
        selectCategory(ad.getCategory());
        selectCondition(ad.getCondition());
        selectCity(ad.getCity());
        setDescription(ad.getDescription());
        setPrice(ad.getPrice());
        clickPublishButton();
        HomePage homepage = page(HomePage.class);
        return homepage;
    }
    public CreateAdPage shouldBeOpened() {
        nameInput.shouldBe(visible);
        publishButton.shouldBe(visible);
        return this;
    }
}