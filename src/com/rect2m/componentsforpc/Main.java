package com.rect2m.componentsforpc;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import com.rect2m.componentsforpc.persistence.entity.impl.User;
import com.rect2m.componentsforpc.persistence.repository.RepositoryFactory;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Set<User> users = generateUsers(10);

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
                .getRepositoryFactory(RepositoryFactory.JSON);
        UserRepository userRepository = jsonRepositoryFactory.getUserRepository();

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
            String phoneNumber = faker.phoneNumber().cellPhone();
            String address = faker.address().streetAddress();
            User user = User.builder()
                    .id(id)
                    .userName(username)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build();

            users.add(user);
        }

        return users;
    }
}