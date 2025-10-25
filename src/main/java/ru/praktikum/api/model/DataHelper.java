package ru.praktikum.api.model;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private final Faker faker;
    private final Random random;

    private static final List<String> CITIES = List.of(
            "Москва",
            "Санкт-Петербург",
            "Новосибирск",
            "Екатеринбург",
            "Нижний Новгород",
            "Казань"
    );

    private static final List<String> CATEGORIES = List.of(
            "Авто",
            "Книги",
            "Садоводство",
            "Хобби",
            "Технологии"
    );

    public DataHelper() {
        this.faker = new Faker(new Locale("ru"));
        this.random = new Random();
    }

    public UserRegisterRequest createRandomUser() {

        UserCreated userCreated = UserCreated.random();

        return new UserRegisterRequest(
                userCreated.getEmail(),
                userCreated.getPassword(),
                userCreated.getSubmitPassword()
        );
    }

    public CreateAdRequest createRandomAd() {
        String productName = generateProductName();
        String category = getRandomCategory();
        String condition = "Б/У";
        String city = getRandomCity();
        String description = generateDescription();
        int price = generatePrice();

        return new CreateAdRequest(productName, category, condition, city, description, price);
    }

    private String getRandomCategory() {
        return CATEGORIES.get(random.nextInt(CATEGORIES.size()));
    }

    private String getRandomCity() {
        return CITIES.get(random.nextInt(CITIES.size()));
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