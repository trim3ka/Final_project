package ru.praktikum.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreated {
    private String email;
    private String password;  // было int
    private String submitPassword;

    // Метод для генерации случайного юзера
    public static UserCreated random() {
        var rnd = new Random();
        String password = String.valueOf(rnd.nextInt(1000000));

        return new UserCreated(
                "m_v_" + rnd.nextInt(1000) + "@ya.ru",
                password, password
        );
    }
}