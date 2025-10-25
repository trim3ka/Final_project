package ru.praktikum.pages;

import com.codeborne.selenide.SelenideElement;
import ru.praktikum.constants.Urls;
import ru.praktikum.api.model.UserRegisterRequest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    // Селекторы для формы регистрации
    private final SelenideElement emailInput = $x("//input[@name='email']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement submitPasswordInput = $x("//input[@name='submitPassword']");
    private final SelenideElement createAccountButton = $x("//button[text()='Создать аккаунт']");
    private final SelenideElement registrationErrorLabel = $x("//span[text()='Ошибка']");

    public RegistrationPage openViaUI() {
        System.out.println("🔄 Открываем форму регистрации через UI");

        open(Urls.HOME_PAGE_URL);
        System.out.println("✅ Открыта главная страница");

        System.out.println("Нажимаем 'Вход и регистрация'");
        $x("//button[text()='Вход и регистрация']")
                .shouldBe(visible, enabled)
                .click();

        System.out.println("Нажимаем 'Нет аккаунта'");
        $x("//button[text()='Нет аккаунта']")
                .shouldBe(visible, enabled)
                .click();

        System.out.println("Ждем загрузки формы регистрации");
        emailInput.shouldBe(visible);
        System.out.println("✅ Форма регистрации загружена");

        return this;
    }

    public void setEmail(String email) {
        System.out.println("✏️ Вводим email: " + email);
        emailInput.shouldBe(visible, editable).setValue(email);
    }

    public void setPassword(String password) {
        System.out.println("✏️ Вводим пароль: " + password);
        passwordInput.shouldBe(visible, editable).setValue(password);
    }

    public void setSubmitPassword(String submitPassword) {
        System.out.println("✏️ Подтверждаем пароль: " + submitPassword);
        submitPasswordInput.shouldBe(visible, editable).setValue(submitPassword);
    }

    public void clickCreateAccountButton() {
        System.out.println("🖱️ Нажимаем кнопку 'Создать аккаунт'");
        createAccountButton.shouldBe(visible, enabled).click();
    }

    public HomePage registerUser(UserRegisterRequest user) {
        fillInRegistrationData(user);
        return page(HomePage.class);
    }

    public RegistrationPage tryRegisterUser(UserRegisterRequest user){
        System.out.println("🔄 Пытаемся зарегистрировать юзера: " + user.getEmail());
        fillInRegistrationData(user);
        return this;
    }

    public RegistrationPage shouldBeFailedRegistration() {
        System.out.println("🔍 Проверяем наличие ошибки регистрации");
        registrationErrorLabel.shouldBe(visible);
        System.out.println("✅ Ошибка регистрации отображена");
        return this;
    }

    private void fillInRegistrationData(UserRegisterRequest user){
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setSubmitPassword(user.getSubmitPassword());
        clickCreateAccountButton();
    }
}