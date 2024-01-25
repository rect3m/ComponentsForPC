package com.rect2m.componentsforpc;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.entity.impl.User.Role;
import com.rect2m.componentsforpc.persistence.repository.RepositoryFactory;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Set<User> users = generateUsers(1);

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
                .getRepositoryFactory(RepositoryFactory.JSON);
        UserRepository userRepository = jsonRepositoryFactory.getUserRepository();
//
        int i = 0;
        for (User user : users) {
            userRepository.add(user);
            if (i == 3) {
                userRepository.remove(user);
            }
            if (i == 5) {
                userRepository.remove(user);
            }
            if (i == 7) {
                userRepository.remove(user);
            }
            i++;
        }

        userRepository.findAll().forEach(out::println);

//        AuthService authService = new AuthService(userRepository);
//        authService.authenticate("user1", "password");
//        SignUpService signUpService = new SignUpService(userRepository);
//        try {
//            signUpService.signUp("user1", "password", "example@mail.com",
//                    "2619494198", "asdasdsad",
//                    () -> {
//                        System.out.print("Введіть код підтвердження: ");
//                        try (Scanner scanner = new Scanner(System.in)) {
//                            return scanner.nextLine();
//                        } /*catch (Exception e) {
//                        System.out.println("Помилка формату.");
//
//                    }*/
//                    });
//        } catch (SignUpException e) {
//            System.err.println(e.getMessage());
//        }

        // Цей рядок, має бути обовязково в кінці метода main!!!!
        jsonRepositoryFactory.commit();
    }

    public static Set<User> generateUsers(int count) {
        Set<User> users = new HashSet<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID id = UUID.randomUUID();
            String username = faker.name().username();
            String password = faker.internet().password();
            String email = faker.internet().emailAddress();
            String phoneNumber = faker.phoneNumber().cellPhone();
            String address = faker.address().streetAddress();
            String role = String.valueOf(Role.valueOf("ADMIN"));
            User user = User.builder()
                    .id(id)
                    .userName(username)
                    .password(password)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .role(Role.valueOf(role))
                    .build();

            users.add(user);
        }

        return users;
    }
}