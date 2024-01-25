package com.rect2m.componentsforpc.persistence.repository;

import com.rect2m.componentsforpc.persistence.repository.contracts.OrderRepository;
import com.rect2m.componentsforpc.persistence.repository.contracts.PaymentRepository;
import com.rect2m.componentsforpc.persistence.repository.contracts.ProductRepository;
import com.rect2m.componentsforpc.persistence.repository.contracts.UserRepository;
import com.rect2m.componentsforpc.persistence.repository.impl.json.JsonRepositoryFactory;
import org.apache.commons.lang3.NotImplementedException;

public abstract class RepositoryFactory {

    public static final int JSON = 1;
    public static final int XML = 2;
    public static final int POSTGRESQL = 3;

    public static RepositoryFactory getRepositoryFactory(int whichFactory) {
        return switch (whichFactory) {
            case JSON -> JsonRepositoryFactory.getInstance();
            case XML -> throw new NotImplementedException("Робота з XML файлами не реалізована.");
            case POSTGRESQL -> throw new NotImplementedException(
                    "Робота з СУБД PostgreSQL не реалізована.");
            default -> throw new IllegalArgumentException(
                    "Помилка при виборі фабрики репозиторіїв.");
        };
    }

    public abstract OrderRepository getOrderRepository();

    public abstract PaymentRepository getPaymentRepository();

    public abstract ProductRepository getProductRepository();

    public abstract UserRepository getUserRepository();

    public abstract void commit();
}