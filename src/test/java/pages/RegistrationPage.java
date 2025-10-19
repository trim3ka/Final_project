package pages;

import com.codeborne.selenide.SelenideElement;
import constants.Urls;
import api.model.UserRegisterRequest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement emailInput = $x("//input[@name='email']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement submitPasswordInput = $x("//input[@name='submitPassword']");
    private final SelenideElement createAccountButton = $x("//button[text()='Создать аккаунт']");
    private final SelenideElement registrationErrorLabel = $x("//span[text()='Ошибка']");

    public RegistrationPage openPage() {
        open(Urls.REGISTER_PAGE_URL);
        return this;
    }

    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void setSubmitPassword(String submitPassword) {
        submitPasswordInput.setValue(submitPassword);
    }

    public void clickCreateAccountButton() {
        createAccountButton.click();
    }

    public HomePage registerUser(UserRegisterRequest user) {
        fillInRegistrationData(user);
        return page(HomePage.class);
    }

    public RegistrationPage tryRegisterUser(UserRegisterRequest user){
        fillInRegistrationData(user);
        return this;
    }

    public RegistrationPage shouldBeFailedRegistration() {
        registrationErrorLabel.shouldBe(visible);
        return this;
    }

    private void fillInRegistrationData(UserRegisterRequest user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setSubmitPassword(user.getSubmitPassword());
        clickCreateAccountButton();
    }
}