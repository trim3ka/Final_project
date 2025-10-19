package components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.EditAdPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class AdCard {

    private final SelenideElement card;
    private final SelenideElement titleElement;
    private final SelenideElement locationElement;
    private final SelenideElement priceElement;
    private final SelenideElement editButtonElement;
    private final SelenideElement deleteButtonElement;

    private final By titleSelector = By.cssSelector(".about .h2");
    private final By locationSelector = By.cssSelector(".about .h3");
    private final By priceSelector = By.cssSelector(".price .h2");
    private final By editButtonSelector = By.cssSelector(".editButton");
    private final By deleteButtonSelector = By.cssSelector(".deleteButton");

    public AdCard(SelenideElement cardElement) {
        this.card = cardElement;
        this.titleElement = card.$(titleSelector);
        this.locationElement = card.$(locationSelector);
        this.priceElement = card.$(priceSelector);
        this.editButtonElement = card.$(editButtonSelector);
        this.deleteButtonElement = card.$(deleteButtonSelector);
    }

    public String getTitle() {
        return titleElement.shouldBe(visible).getText();
    }

    public String getLocation() {
        return locationElement.shouldBe(visible).getText();
    }

    public String getPrice() {
        return priceElement.shouldBe(visible).getText();
    }

    public int getPriceAsInt() {
        String priceText = getPrice().replace("₽", "").trim();
        return Integer.parseInt(priceText);
    }

    public void clickEdit() {
        editButtonElement.shouldBe(exist).shouldBe(visible).click();
    }

    public void clickDelete() {
        deleteButtonElement.shouldBe(visible).click();
    }

    public EditAdPage editAd() {
        clickEdit();
        EditAdPage editAdPage = page(EditAdPage.class);
        return editAdPage;
    }

    public AdCard shouldHaveEditButton() {
        if (!editButtonElement.exists()) {
            throw new AssertionError("В карточке объявления нет кнопки для редактирования");
        }
        editButtonElement.shouldBe(visible.because("Кнопка редактирования должна быть видима"));
        return this;
    }

    public AdCard shouldHaveDeleteButton() {
        if (!deleteButtonElement.exists()) {
            throw new AssertionError("В карточке объявления нет кнопки удаления");
        }
        deleteButtonElement.shouldBe(visible.because("Кнопка удаления должна быть видима"));
        return this;
    }
}