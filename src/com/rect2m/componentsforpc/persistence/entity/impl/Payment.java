package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import com.rect2m.componentsforpc.persistence.entity.ErrorTemplates;
import com.rect2m.componentsforpc.persistence.exception.EntityArgumentException;
import java.time.LocalDate;
import java.util.StringJoiner;
import java.util.UUID;

public class Payment extends Entity {

    private UUID id;
    private int amount;
    private UUID orderID;
    private LocalDate date = LocalDate.now();

    private Payment(UUID uuid, int amount, UUID orderID, LocalDate date) {
        this.id = uuid;
        this.amount = amount;
        this.orderID = orderID;
        this.date = date;
    }

    public Payment() {
    }

    public static PaymentBuilderUuid builder() {
        return uuid -> amount -> orderID -> date -> () -> new Payment(uuid, amount,
                orderID, date);
    }

    private static boolean isAmountValid(int amount) {
        // Перевірка, чи введене число складається тільки з цифр
        return String.valueOf(amount).matches("\\d+");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        final String templateName = "кількість";

        if (amount == 0) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (String.valueOf(amount).length() < 1) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 1));
        }
        if (String.valueOf(amount).length() > 30) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 30));
        }
        if (isAmountValid(amount)) {
            errors.add(ErrorTemplates.ONLY_NUMBERS.getTemplate().formatted(templateName, 30));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.amount = amount;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("uuid = " + id)
                .add("amount" + amount)
                .add("orderID" + orderID)
                .add("date" + date)
                .toString();
    }

    public interface PaymentBuilderUuid {

        PaymentBuilderAmount uuid(UUID id);
    }

    public interface PaymentBuilderAmount {

        PaymentBuilderOrderID amount(int amount);
    }

    public interface PaymentBuilderOrderID {

        PaymentBuilderDate orderID(UUID orderID);
    }

    public interface PaymentBuilderDate {

        PaymentBuilder date(LocalDate date);
    }

    public interface PaymentBuilder {

        Payment build();
    }
}
