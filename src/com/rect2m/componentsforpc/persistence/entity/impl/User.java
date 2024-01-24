package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import java.util.StringJoiner;
import java.util.UUID;

public class User extends Entity {

    private String userName;
    private String password;
    private String phoneNumber;
    private String address;

    public User(UUID id, String userName, String password, String phoneNumber, String address) {
        super(id);
        setUserName(userName);
        this.password = password;
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    public static UserBuilderUuid builder() {
        return id -> userName -> password -> phoneNumber -> address -> () -> new User(id,
                userName, password, phoneNumber, address);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        //if (Validation.isValidUserName(userName)) {
        this.userName = userName;
//        } else {
//            throw new IllegalArgumentException("Invalid user name");
//        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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