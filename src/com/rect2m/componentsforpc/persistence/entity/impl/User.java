package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import com.rect2m.componentsforpc.persistence.entity.ErrorTemplates;
import com.rect2m.componentsforpc.persistence.exception.EntityArgumentException;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.regex.Pattern;

public class User extends Entity {

    //private UUID id;
    private String userName;
    private String password;
    private String phoneNumber;
    private String address;

    public User(UUID id, String userName, String password, String phoneNumber, String address) {
        super(id);
        setUsername(userName);
        this.password = validatedPassword(password);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    public User() {
    }

    public static UserBuilderUuid builder() {
        return id -> userName -> password -> phoneNumber -> address -> () -> new User(id,
                userName, password, phoneNumber, address);
    }

//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }

    private static boolean isPhoneNumberValid(int phoneNumber) {
        // Перевірка, чи введене число складається тільки з цифр
        return String.valueOf(phoneNumber).matches("\\d+");
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        final String templateName = "логіну";

        if (userName.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (userName.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (userName.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 24));
        }
        var pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        if (pattern.matcher(userName).matches()) {
            errors.add(ErrorTemplates.ONLY_LATIN.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private String validatedPassword(String password) {
        final String templateName = "паролю";

        if (password.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (password.length() < 8) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 8));
        }
        if (password.length() > 32) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 32));
        }
        var pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$");
        if (pattern.matcher(password).matches()) {
            errors.add(ErrorTemplates.PASSWORD.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
//        final String templateName = "номеру телефону";
//
//        if (phoneNumber <= 0) {
//            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
//        }
//        if (String.valueOf(phoneNumber).length() < 10) {
//            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 10));
//        }
//        if (String.valueOf(phoneNumber).length() > 10) {
//            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 10));
//        }
//        if (isPhoneNumberValid(phoneNumber)) {
//            errors.add(ErrorTemplates.ONLY_NUMBERS.getTemplate().formatted(templateName, 10));
//        }
//
//        if (!this.errors.isEmpty()) {
//            throw new EntityArgumentException(errors);
//        }

        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        final String templateName = "адреси";

        if (address.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (address.length() < 8) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 6));
        }
        if (address.length() > 32) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 32));
        }
        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("id = " + id)
                .add(userName)
                .add(password)
                .add("phoneNumber=" + phoneNumber)
                .add(address)
                .toString();
    }

    public interface UserBuilderUuid {

        UserBuilderUserName id(UUID id);
    }

    public interface UserBuilderUserName {

        UserBuilderPassword userName(String userName);
    }

    public interface UserBuilderPassword {

        UserBuilderPhoneNumber password(String password);
    }

    public interface UserBuilderPhoneNumber {

        UserBuilderAddress phoneNumber(String phoneNumber);
    }

    public interface UserBuilderAddress {

        UserBuilder address(String address);
    }

    public interface UserBuilder {

        User build();
    }
}