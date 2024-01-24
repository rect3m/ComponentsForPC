package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import com.rect2m.componentsforpc.persistence.entity.ErrorTemplates;
import com.rect2m.componentsforpc.persistence.exception.EntityArgumentException;
import java.util.StringJoiner;
import java.util.UUID;

public class Product extends Entity {

    private UUID id;
    private String productName;
    private String description;
    private String manufacturer;
    private int price;

    private Product(UUID uuid, String productName, String description, String manufacturer,
            int price) {
        this.id = uuid;
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public Product() {
    }

    public static ProductBuilderUuid builder() {
        return uuid -> productName -> description -> manufacturer -> price -> () -> new Product(
                uuid, productName, description, manufacturer, price);
    }

    private static boolean isPriceValid(int price) {
        // Перевірка, чи введене число складається тільки з цифр
        return String.valueOf(price).matches("\\d+");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        final String templateName = "назва продукту";

        if (productName.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (productName.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (productName.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 30));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        final String templateName = "опис продукту";

        if (description.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (description.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (description.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 50));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        final String templateName = "виробник продукту";

        if (manufacturer.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (manufacturer.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (manufacturer.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 30));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        final String templateName = "ціна продукту";

        if (price <= 0) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (String.valueOf(price).length() < 2) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 2));
        }
        if (String.valueOf(price).length() > 10) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 10));
        }
        if (isPriceValid(price)) {
            errors.add(ErrorTemplates.ONLY_NUMBERS.getTemplate().formatted(templateName, 10));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("uuid = " + id)
                .add(productName)
                .add(description)
                .add(manufacturer)
                .add("price=" + price + "usd")
                .toString();
    }

    public interface ProductBuilderUuid {

        ProductBuilderProductName uuid(UUID id);
    }

    public interface ProductBuilderProductName {

        ProductBuilderDescription productName(String productName);
    }

    public interface ProductBuilderDescription {

        ProductBuilderManufacturer description(String description);
    }

    public interface ProductBuilderManufacturer {

        ProductBuilderPrice manufacturer(String manufacturer);
    }

    public interface ProductBuilderPrice {

        ProductBuilder price(int price);
    }

    public interface ProductBuilder {

        Product build();
    }
}
