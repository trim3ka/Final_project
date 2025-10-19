package stepdefinitions;

import api.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {

    private final TestContext context;

    public LoginStep(TestContext context) {
        this.context = context;
    }

    @When("Пользователь открывает страницу авторизации")
    public void openLoginPage() {
        context.loginPage = context.loginPage.openPage();
    }

    @When("Пользователь вводит валидные учетные данные")
    public void enterValidCredentials() {
        context.loginPage.loginUser(context.createdUser);
    }

    @When("Пользователь выполняет авторизацию")
    public void performLogin() {
        context.loginPage.clickLoginButton();
    }

    @Then("Пользователь успешно авторизован")
    public void userSuccessfullyLoggedIn() {
        context.homePage.getHeader().shouldBeAuthorized();
    }
}
