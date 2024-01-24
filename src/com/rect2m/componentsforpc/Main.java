package com.rect2m.componentsforpc;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.util.LocalDateSerializer;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    //    public static void main(String[] args) {
//        User user = User.builder()
//                .id(UUID.randomUUID())
//                .userName("loxa_.")
//                .password("va1212121")
//                .phoneNumber(111111)
//                .address("valid")
//                .build();
//        System.out.println(user);
//    }
    public static void main(String[] args) {
        List<User> users = generateUsers(10);

        // Виведемо створених користувачів
        for (User user : users) {
            out.println(user);
        }

        writeUsersToJsonFile(users, "users.json");
    }

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID id = UUID.randomUUID();
            String username = faker.name().username();
            String password = faker.internet().password();
            String phoneNumber = faker.phoneNumber().cellPhone();
            String address = faker.address().streetAddress();
            LocalDate birthday = faker.date()
                    .birthday()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            User user = new User(id, username, password, phoneNumber, address);
            users.add(user);
        }

        return users;
    }

    public static void writeUsersToJsonFile(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Створюємо Gson з красивим виведенням
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .setPrettyPrinting().create();

            // Перетворюємо колекцію користувачів в JSON та записуємо у файл
            gson.toJson(users, writer);

            System.out.println("Колекцію користувачів збережено в файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}