package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class Order extends Entity {

    private List<Product> productList;
    private LocalDateTime purchaseDateTime = LocalDateTime.now();
    private int totalCost;

    private Order(UUID id, List<Product> productList, LocalDateTime purchaseDateTime,
            int totalCost) {
        super(id);
        this.productList = productList;
        this.purchaseDateTime = purchaseDateTime;
        this.totalCost = totalCost;
    }

    public static Order.OrderBuilderUuid builder() {
        return id -> productList -> purchaseDateTime -> totalCost -> () -> new Order(id,
                productList, purchaseDateTime,
                totalCost);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("uuid= " + id)
                .add("product=" + productList)
                .add("purchaseDateTime=" + purchaseDateTime)
                .add("totalCost=" + totalCost + "грн")
                .toString();
    }

    public interface OrderBuilderUuid {

        OrderBuilderProductList uuid(UUID id);
    }

    public interface OrderBuilderProductList {

        OrderBuilderPurchaseDateTime productList(List<Product> productList);
    }

    public interface OrderBuilderPurchaseDateTime {

        OrderBuilderTotalCost purchaseDateTime(LocalDateTime purchaseDateTime);
    }

    public interface OrderBuilderTotalCost {

        OrderBuilder totalCost(int totalCost);
    }

    public interface OrderBuilder {

        Order build();
    }
}