package ru.praktikum.stepdefinitions;

import ru.praktikum.api.model.CreateAdRequest;
import ru.praktikum.api.model.UserRegisterRequest;
import ru.praktikum.api.model.DataHelper;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.praktikum.pages.*;

public class TestContext {

    public DataHelper dataHelper;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public CreateAdPage createAdPage;
    public ProfilePage profilePage;
    public EditAdPage editAdPage;

    public UserRegisterRequest createdUser;
    public CreateAdRequest createdAd;
    public String deletedAdTitle;

    public TestContext() {
        setup();
    }

    private void setup() {
        configureDriver();

        this.dataHelper = new DataHelper();
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.registrationPage = new RegistrationPage();
        this.createAdPage = new CreateAdPage();
        this.profilePage = new ProfilePage();
        this.editAdPage = new EditAdPage();
    }

    private void configureDriver() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.timeout = 3000;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    public void cleanup() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}