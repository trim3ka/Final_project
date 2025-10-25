package ru.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import ru.praktikum.api.model.UserRegisterRequest;
import ru.praktikum.constants.Urls;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement emailInput = $("input[name='email']");
    private final SelenideElement passwordInput = $("input[name='password']");
    private final SelenideElement loginButton = $x("//button[text()='Войти']");
    private final SelenideElement registerButton = $x("//button[text()='Нет аккаунта']");

    public LoginPage openPage() {
        open(Urls.LOGIN_PAGE_URL);
        return this;
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public HomePage loginUser(UserRegisterRequest user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickLoginButton();
        HomePage homePage = page(HomePage.class);
        return homePage;
    }

    public RegistrationPage clickRegisterButton() {
        registerButton.click();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        return registrationPage;
    }
}