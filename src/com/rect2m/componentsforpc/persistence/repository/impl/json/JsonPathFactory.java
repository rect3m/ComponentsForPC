package com.rect2m.componentsforpc.persistence.repository.impl.json;

import java.nio.file.Path;

/**
 * Фабрика об'єктів Path для DAO.
 */
enum JsonPathFactory {
    USERS("users.json"),
    ORDERS("orders.json"),
    PAYMENTS("payments.json"),
    PRODUCTS("products.json");

    private static final String DATA_DIRECTORY = "Data";
    private final String fileName;

    JsonPathFactory(String fileName) {
        this.fileName = fileName;
    }

    public Path getPath() {
        return Path.of(DATA_DIRECTORY, this.fileName);
    }
}