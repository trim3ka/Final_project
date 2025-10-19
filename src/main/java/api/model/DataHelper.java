package api;

import api.model.CreateAdRequest;
import com.github.javafaker.Faker;
import api.model.UserRegisterRequest;
import java.util.Locale;

public class DataHelper {
    private final Faker faker;

    public DataHelper() {
        this.faker = new Faker(new Locale("en"));
    }

    public UserRegisterRequest createRandomUser() {
        String email = generateEmail();
        String password = generatePassword();
        String submitPassword = password;
        return new UserRegisterRequest(email, password, submitPassword);
    }

    public CreateAdRequest createRandomAd() {
        String productName = generateProductName();
        String category = "Хобби";
        String city = "Москва";
        String condition = "Новый";
        String description = generateDescription();
        int price = generatePrice();
        return new CreateAdRequest(productName, category, city, condition, description, price);
    }

    private String generateEmail() {
        return faker.internet().emailAddress();
    }

    private String generatePassword() {
        return faker.internet().password();
    }

    private String generateProductName() {
        return faker.commerce().productName();
    }

    private String generateDescription() {
        return faker.lorem().sentence(10);
    }

    private int generatePrice() {
        return faker.number().numberBetween(1, 50000);
    }
}