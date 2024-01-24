package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import com.rect2m.componentsforpc.persistence.entity.ErrorTemplates;
import com.rect2m.componentsforpc.persistence.exception.EntityArgumentException;
import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class Order extends Entity {

    private UUID id;
    private LocalDateTime purchaseDateTime = LocalDateTime.now();
    private int totalCost;

    private Order(UUID uuid, LocalDateTime purchaseDateTime, int totalCost) {
        this.id = uuid;
        this.purchaseDateTime = purchaseDateTime;
        this.totalCost = totalCost;
    }

    public Order() {
    }

    public static Order.OrderBuilderUuid builder() {
        return uuid -> purchaseDateTime -> totalCost -> () -> new Order(uuid, purchaseDateTime,
                totalCost);
    }

    private static boolean isTotalCostValid(int totalCost) {
        // Перевірка, чи введене число складається тільки з цифр
        return String.valueOf(totalCost).matches("\\d+");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        final String templateName = "кількість";

        if (totalCost == 0) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (String.valueOf(totalCost).length() < 1) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 1));
        }
        if (String.valueOf(totalCost).length() > 30) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 30));
        }
        if (isTotalCostValid(totalCost)) {
            errors.add(ErrorTemplates.ONLY_NUMBERS.getTemplate().formatted(templateName, 30));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("uuid = " + id)
                .add("purchaseDateTime=" + purchaseDateTime)
                .add("totalCost=" + totalCost + "грн")
                .toString();
    }

    public interface OrderBuilderUuid {

        OrderBuilderPurchaseDateTime uuid(UUID id);
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