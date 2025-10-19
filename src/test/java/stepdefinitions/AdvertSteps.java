package stepdefinitions;

import api.DataHelper;
import api.TestContext;
import components.AdCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProfilePage;

public class AdvertSteps {

    private final TestContext context;
    private final DataHelper dataHelper;

    public AdvertSteps(TestContext context) {
        this.context = context;
        this.dataHelper = new DataHelper();
    }

    @Given("Пользователь авторизован в системе")
    public void userIsLoggedIn() {
        context.homePage = context.loginPage.openPage()
                .loginUser(context.createdUser);
        context.homePage.getHeader()
                .shouldBeAuthorized();
    }

    @Given("У пользователя есть созданное объявление")
    public void userHasCreatedAd() {
        context.createdAd = dataHelper.createRandomAd();
        context.createAdPage = context.homePage.getHeader()
                .clickCreateAdButton();
        context.homePage = context.createAdPage.createAd(context.createdAd);
    }

    @When("Пользователь создает новое объявление")
    public void userCreatesNewAd() {
        context.createdAd = dataHelper.createRandomAd();
        context.createAdPage = context.homePage.getHeader()
                .clickCreateAdButton();
        context.createAdPage.createAd(context.createdAd);
    }

    @When("Пользователь редактирует объявление")
    public void userEditsAd() {
        ProfilePage profilePage = context.homePage.getHeader()
                .shouldBeAuthorized()
                .clickProfileButton();
        profilePage.shouldHaveAdvertisements();
        AdCard firstAd = profilePage.getAllAdvertisements().get(0);
        firstAd.shouldHaveEditButton();
        context.editAdPage = firstAd.editAd();
    }

    @When("Пользователь просматривает свои объявления")
    public void userViewsAds() {
        context.profilePage = context.homePage.getHeader()
                .shouldBeAuthorized()
                .clickProfileButton();
    }

    @Then("Объявление отображается в профиле пользователя")
    public void adDisplayedInProfile() {
        context.profilePage.shouldHaveAdvertisements();
    }

    @Then("Открывается форма редактирования объявления")
    public void editAdFormOpens() {
        context.editAdPage.shouldHaveTitle("Редактирование объявления");
    }

    @Then("У объявления доступна кнопка удаления")
    public void deleteButtonAvailable() {
        ProfilePage profilePage = context.homePage.getHeader()
                .shouldBeAuthorized()
                .clickProfileButton();
        profilePage.shouldHaveAdvertisements();
        AdCard firstAd = profilePage.getAllAdvertisements().get(0);
        firstAd.shouldHaveDeleteButton();
    }
}