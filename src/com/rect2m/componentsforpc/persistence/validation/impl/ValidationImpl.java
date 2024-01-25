package com.rect2m.componentsforpc.persistence.validation.impl;

import com.rect2m.componentsforpc.persistence.validation.exception.ValidationIOException;

public class ValidationImpl {

    public static boolean isValidUserName(String userName) {
        boolean validUserName = userName == null && userName.matches("^[a-zA-Z0-9]{6,24}$");
        if (!validUserName) {
            throw new ValidationIOException("Неправильно введене ім'я");
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        // Пароль повинен мати від 8 до 30 символів і містити хоча б одну велику літеру, одну маленьку літеру та одну цифру
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,30}$";
        return password != null && password.matches(passwordRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Код країни є необов'язковим
        // Може мати формат: +123456789 або 123456789
        String phoneRegex = "^(\\+\\d{1,4})?\\d{10}$";
        return phoneNumber != null && phoneNumber.matches(phoneRegex);
    }

    public static boolean isValidAddress(String address) {
        // Довжина може бути від 5 до 100 символів
        // Може містити літери, цифри, пробіли та окремі символи
        String addressRegex = "^[a-zA-Z0-9.,\\s]{5,100}$";
        return address != null && address.matches(addressRegex);
    }

    public static boolean isValidProductName(String productName) {
        // Дозволяє літери, цифри, пробіли, тире та апостроф
        // Довжина від 2 до 50 символів
        String productNameRegex = "^[a-zA-Z0-9\\s'-]{2,50}$";
        return productName != null && productName.matches(productNameRegex);
    }

    public static boolean isValidDescription(String description) {
        // Дозволяє літери, цифри, пробіли, коми, крапки, тире та інші допустимі символи
        // Довжина від 2 до 500 символів
        String descriptionRegex = "^[a-zA-Z0-9\\s,.'-]{2,500}$";
        return description != null && description.matches(descriptionRegex);
    }

    public static boolean isValidManufacturer(String manufacturer) {
        // Дозволяє літери, цифри, пробіли, коми, крапки, апостроф, тире та інші допустимі символи
        // Довжина від 2 до 50 символів
        String manufacturerRegex = "^[a-zA-Z0-9\\s,.'-]{2,50}$";
        return manufacturer != null && manufacturer.matches(manufacturerRegex);
    }

    public static boolean isValidPrice(int price) {
        // Price must be a positive integer or have a positive fractional part
        return price >= 0;
    }

    public static boolean isValidQuantity(int amount) {
        return amount >= 0;
    }

}
