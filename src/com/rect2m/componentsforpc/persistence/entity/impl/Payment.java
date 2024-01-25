package com.rect2m.componentsforpc.persistence.entity.impl;

import com.rect2m.componentsforpc.persistence.entity.Entity;
import java.time.LocalDate;
import java.util.StringJoiner;
import java.util.UUID;

public class Payment extends Entity {

    private int amount;
    private final Order orderID;
    private LocalDate date = LocalDate.now();

    private Payment(UUID id, int amount, Order orderID, LocalDate date) {
        super(id);
        this.amount = amount;
        this.orderID = orderID;
        this.date = date;
    }

    public static PaymentBuilderUuid builder() {
        return id -> amount -> orderID -> date -> () -> new Payment(id, amount,
                orderID, date);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order getOrderID() {
        return orderID;
    }

    public void setOrderID(Order orderID) {

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

        PaymentBuilderDate orderID(Order orderID);
    }

    public interface PaymentBuilderDate {

        PaymentBuilder date(LocalDate date);
    }

    public interface PaymentBuilder {

        Payment build();
    }
}
