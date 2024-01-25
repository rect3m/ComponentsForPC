package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import com.rect2m.componentsforpc.persistence.validation.exception.ValidationIOException;
import com.rect2m.componentsforpc.persistence.validation.impl.ValidationImpl;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

public class User extends Entity {

    private final Role role;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;

    public User(UUID id, String userName, String password, String email, String phoneNumber,
            String address, Role role) {
        super(id);
        setUserName(userName);
        this.email = email;
        this.password = password;
        setPhoneNumber(phoneNumber);
        setAddress(address);
        this.role = role;
    }

    public static UserBuilderUuid builder() {
        return id -> userName -> password -> email -> phoneNumber -> address -> role -> () -> new User(
                id, userName, password, email, phoneNumber, address, role);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        try {
            if (ValidationImpl.isValidUserName(userName)) {
                this.userName = userName;
            }
        } catch (ValidationIOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("id = " + id)
                .add(userName)
                .add(password)
                .add(email)
                .add("phoneNumber=" + phoneNumber)
                .add(address)
                .toString();
    }

    public enum Role {
        ADMIN("admin", Map.of(
                EntityName.ORDER, new Permission(true, true, true, true),
                EntityName.PAYMENT, new Permission(true, true, true, true),
                EntityName.PRODUCT, new Permission(true, true, true, true),
                EntityName.USER, new Permission(true, true, true, true))),
        GENERAL("general", Map.of(
                EntityName.ORDER, new Permission(true, false, true, true),
                EntityName.PAYMENT, new Permission(false, false, false, true),
                EntityName.PRODUCT, new Permission(false, false, false, true),
                EntityName.USER, new Permission(false, false, false, false)));

        private final String name;
        private final Map<EntityName, Permission> permissions;

        Role(String name, Map<EntityName, Permission> permissions) {
            this.name = name;
            this.permissions = permissions;
        }

        public enum EntityName {ORDER, PAYMENT, PRODUCT, USER}

        private record Permission(boolean canAdd, boolean canEdit, boolean canDelete,
                                  boolean canRead) {

        }
    }

    public interface UserBuilderUuid {

        UserBuilderUserName id(UUID id);
    }

    public interface UserBuilderUserName {

        UserBuilderPassword userName(String userName);
    }

    public interface UserBuilderPassword {

        UserBuilderEmail password(String password);
    }

    public interface UserBuilderEmail {

        UserBuilderPhoneNumber email(String email);
    }

    public interface UserBuilderPhoneNumber {

        UserBuilderAddress phoneNumber(String phoneNumber);
    }

    public interface UserBuilderAddress {

        UserBuilderRole address(String address);
    }

    public interface UserBuilderRole {

        UserBuilder role(Role role);
    }

    public interface UserBuilder {

        User build();
    }
}