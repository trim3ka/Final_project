package stepdefinitions;

import api.TestContext;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before(order = 1)
    public void beforeScenario() {
    }

    @After
    public void afterScenario() {
        Selenide.closeWebDriver();
        context.cleanup();
    }
}
