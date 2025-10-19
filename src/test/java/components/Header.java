package components;

import com.codeborne.selenide.SelenideElement;
import pages.CreateAdPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Header {

    private final SelenideElement loginAndRegisterButton = $x("//button[text()='Вход и регистрация']");
    private final SelenideElement profileButton = $("button.circleSmall");
    private final SelenideElement userNameLabel = $(".profileText.name");
    private final SelenideElement logoutButton = $x("//button[text()='Выйти']");
    private final SelenideElement createAdButton = $x("//button[text()='Разместить объявление']");

    public String getUserName() {
        return userNameLabel.getText();
    }

    public LoginPage clickLoginAndRegisterButton() {
        loginAndRegisterButton.click();
        LoginPage loginPage = page(LoginPage.class);
        return loginPage;
    }

    public ProfilePage clickProfileButton() {
        profileButton.click();
        ProfilePage profilePage = page(ProfilePage.class);
        return profilePage;
    }

    public Header shouldNotBeAuthorized() {
        loginAndRegisterButton.shouldBe(visible);
        profileButton.shouldNotBe(visible);
        userNameLabel.shouldNotBe(visible);
        logoutButton.shouldNotBe(visible);
        return this;
    }

    public HomePage clickLogoutButton() {
        logoutButton.click();
        HomePage homePage = page(HomePage.class);
        return homePage;
    }

    public CreateAdPage clickCreateAdButton() {
        createAdButton.click();
        CreateAdPage createAdPage = page(CreateAdPage.class);
        return createAdPage;
    }

    public Header shouldBeAuthorized() {
        profileButton.shouldBe(visible);
        userNameLabel.shouldBe(visible);
        logoutButton.shouldBe(visible);
        return this;
    }
}