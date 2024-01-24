package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import java.util.StringJoiner;
import java.util.UUID;

public class Product extends Entity {

    private String productName;
    private String description;
    private String manufacturer;
    private int price;

    private Product(UUID id, String productName, String description, String manufacturer,
            int price) {
        super(id);
        this.productName = productName;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public static ProductBuilderUuid builder() {
        return id -> productName -> description -> manufacturer -> price -> () -> new Product(
                id, productName, description, manufacturer, price);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("id = " + id)
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