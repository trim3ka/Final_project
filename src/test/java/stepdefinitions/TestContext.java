package api;

import api.client.AuthClient;
import api.model.CreateAdRequest;
import api.model.UserRegisterRequest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import constants.Urls;
import pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestContext {

    public AuthClient authClient;
    public api.DataHelper dataHelper;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public CreateAdPage createAdPage;
    public ProfilePage profilePage;
    public EditAdPage editAdPage;

    public UserRegisterRequest createdUser;
    public CreateAdRequest createdAd;
    public Response lastApiResponse;

    public TestContext() {
        setup();
    }

    private void setup() {
        configureDriver();
        this.authClient = new AuthClient(Urls.BASE_URI);
        this.dataHelper = new api.DataHelper();
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

    public void registerUserViaApi(UserRegisterRequest user) {
        lastApiResponse = authClient.registerUser(user);
        lastApiResponse.then().statusCode(HttpStatus.SC_CREATED);
    }

    public void cleanup() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }
}

