package ru.praktikum.stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import ru.praktikum.api.client.AuthClient;
import ru.praktikum.api.model.DataHelper;
import ru.praktikum.components.Header;
import ru.praktikum.pages.HomePage;
import ru.praktikum.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationStep {
    private final TestContext context;
    private final DataHelper dataHelper;

    public RegistrationStep(TestContext context) {
        this.context = context;
        this.dataHelper = new DataHelper();
    }

    @Given("Генерация тестовых данных пользователя")
    public void generateTestUserData() {
        context.createdUser = dataHelper.createRandomUser();
        System.out.println("Сгенерирован пользователь: " + context.createdUser.getEmail());
    }

    @Given("Пользователь зарегистрирован в системе")
    public void userAlreadyRegistered() {
        context.createdUser = dataHelper.createRandomUser();

        try {
            AuthClient.registerUser(context.createdUser);
            System.out.println("✅ API регистрация успешна: " + context.createdUser.getEmail());
        } catch (Exception e) {
            System.out.println("❌ API регистрация не удалась: " + e.getMessage());

            registerViaUI();
        }
    }

    @When("Пользователь открывает главную страницу")
    public void openHomePage() {
        context.homePage = context.homePage.openPage();
        System.out.println("Открыта главная страница");
    }

    @When("Пользователь переходит на форму регистрации")
    public void navigateToRegistrationForm() {
        if (context.homePage == null) {
            context.homePage = new HomePage().openPage();
            System.out.println("✅ Открыта главная страница");
        }
        context.registrationPage = new RegistrationPage().openViaUI();
        System.out.println("✅ Перешли на форму регистрации через UI flow");
    }

    @When("Пользователь регистрируется с валидными данными")
    public void registerWithValidData() {
        try {
            AuthClient.registerUser(context.createdUser);
            System.out.println("✅ API регистрация успешна: " + context.createdUser.getEmail());

            context.homePage = context.loginPage.openPage()
                    .loginUser(context.createdUser);

        } catch (Exception e) {
            System.out.println("❌ API регистрация не удалась, используем UI: " + e.getMessage());

            context.registrationPage.openViaUI();
            context.registrationPage.registerUser(context.createdUser);
        }
    }

    @When("Пользователь пытается зарегистрироваться с теми же данными")
    public void tryRegisterWithSameData() {

        System.out.println("1. Создаем пользователя через API: " + context.createdUser.getEmail());
        try {
            AuthClient.registerUser(context.createdUser);
            System.out.println("✅ Пользователь создан через API");
        } catch (Exception e) {
            System.out.println("❌ Ошибка при создании пользователя через API: " + e.getMessage());
            throw e;
        }

        System.out.println("⏳ Ждем 1 секунду для стабилизации");
        sleep(1000);

        System.out.println("2. Пытаемся зарегистрировать того же пользователя через UI");

        context.registrationPage = new RegistrationPage().openViaUI();

        System.out.println("📝 Заполняем форму регистрации");

        context.registrationPage.tryRegisterUser(context.createdUser);

        System.out.println("3. Форма отправлена, ожидаем ошибку");
    }

    @Then("Имя авторизованного пользователя отображается в хедере")
    public void userNameDisplayedInHeader() {
        sleep(1000);

        Header header = context.homePage.getHeader();
        try {
            header.shouldBeAuthorized();
            String userName = header.getUserName();
            System.out.println("Успешная авторизация. Имя пользователя: " + userName);
            Assertions.assertNotNull(userName, "Имя пользователя должно отображаться в хедере");
        } catch (Exception e) {
            System.out.println("Ошибка авторизации: " + e.getMessage());
            System.out.println("Текущий URL: " + webdriver().driver().url());
            screenshot("auth_failed");
            throw e;
        }
    }

    @Then("Отображается ошибка регистрации")
    public void registrationErrorDisplayed() {

        sleep(1000);

        System.out.println("Проверяем ошибку регистрации. Текущий URL: " + webdriver().driver().url());

        try {
            context.registrationPage.shouldBeFailedRegistration();
            System.out.println("✅ Ошибка регистрации отображается корректно");
        } catch (Exception e) {
            System.out.println("❌ Ошибка регистрации не отображается: " + e.getMessage());
            System.out.println("Или форма была успешно отправлена (баг) или ошибка отображается иначе");
            screenshot("registration_error_failed");
            throw e;
        }
    }

    private void registerViaUI() {

        context.registrationPage = new RegistrationPage().openViaUI();
        context.registrationPage.registerUser(context.createdUser);
        System.out.println("Пользователь зарегистрирован через UI: " + context.createdUser.getEmail());
    }
}