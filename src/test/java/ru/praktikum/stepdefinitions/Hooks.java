package ru.praktikum.stepdefinitions;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import ru.praktikum.api.client.AuthClient;
import ru.praktikum.api.model.UserRegisterRequest;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @After
    public void afterScenario() {
        System.out.println("⚓️ Завершение сценария");

        if (context.createdUser != null) {
            safeCleanupTestUser(context.createdUser);
        }

        Selenide.closeWebDriver();
        context.cleanup();
    }

    private void safeCleanupTestUser(UserRegisterRequest user) {
        try {
            System.out.println("Удаление тестового пользователя: " + user.getEmail());

            boolean deleted = AuthClient.safeDeleteUser(user.getEmail(), user.getPassword());

            if (!deleted) {
                System.out.println("⚠️  Удаление через API не выполнено (‼️ баг). Пользователь " + user.getEmail() + " не удален из системы");
            }

        } catch (Exception e) {
            System.out.println("❌ Ошибка при cleanup пользователя: " + e.getMessage());
            System.out.println("ℹ️  Известная ошибка, не блокер");
        }
    }
}