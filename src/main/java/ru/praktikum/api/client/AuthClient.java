package ru.praktikum.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.constants.Urls;
import ru.praktikum.api.model.UserRegisterRequest;
import ru.praktikum.api.model.UserLogin;

import static io.restassured.RestAssured.given;

public class AuthClient {

    // Регистрация пользователя
    public static ValidatableResponse registerUser(UserRegisterRequest user) {
        System.out.println("Регистрация пользователя через API: " + user.getEmail());
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(Urls.BASE_URI)
                .body(user)
                .when()
                .post(Urls.API_SIGNUP)
                .then().log().all();
    }

    // Логин пользователя
    public static ValidatableResponse loginUser(UserLogin user) {
        System.out.println("Логин пользователя через API: " + user.getEmail());
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(Urls.BASE_URI)
                .body(user)
                .when()
                .post(Urls.API_LOGIN)
                .then().log().all();
    }

    // Получить ID пользователя из ответа логина
    public static int getUserId(String email, String password) {
        UserLogin userLogin = new UserLogin(email, password);
        ValidatableResponse response = loginUser(userLogin);
        return response.extract().jsonPath().getInt("user.id");
    }

    // Получить токен из ответа
    public static String getToken(String email, String password) {
        UserLogin userLogin = new UserLogin(email, password);
        ValidatableResponse response = loginUser(userLogin);
        String accessToken = response.extract().jsonPath().getString("token.access_token");
        System.out.println("Извлеченный токен: " + accessToken);
        return accessToken;
    }

    // Удаление юзера по id
    public static ValidatableResponse deleteUser(int userId, String accessToken) {
        String deleteUrl = Urls.API_DELETE_USER + userId;
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(Urls.BASE_URI)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(deleteUrl)
                .then().log().all();
    }

    public static boolean safeDeleteUser(String email, String password) {
        try {
            // Получаем id юзера
            int userId = getUserId(email, password);
            System.out.println("ID пользователя для удаления: " + userId);

            // Получаем токен
            String accessToken = getToken(email, password);

            if (accessToken != null && !accessToken.isEmpty()) {
                // Удаление юзера
                var response = deleteUser(userId, accessToken);
                int statusCode = response.extract().statusCode();

                if (statusCode == 200 || statusCode == 204) {
                    System.out.println("🚷 Юзер успешно удален: " + email + " (ID: " + userId + ")");
                    return true;
                } else {
                    System.out.println("⚠️  Удаление не удалось. Статус: " + statusCode + ". Эндпоинт не работает (баг)");
                    return false;
                }
            } else {
                System.out.println("Не удалось получить токен для юзера: " + email);
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Ошибка при удалении юзера с email " + email + ": " + e.getMessage());
            System.out.println("ℹ️  Проблема изветсная - эндпоинт удаления не работает");
            return false;
        }
    }
}