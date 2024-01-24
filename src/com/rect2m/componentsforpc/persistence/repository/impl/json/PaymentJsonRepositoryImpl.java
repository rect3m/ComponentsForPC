package com.rect2m.componentsforpc.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.entity.impl.Payment;
import com.rect2m.componentsforpc.persistence.repository.contracts.PaymentRepository;
import java.util.Set;
import java.util.stream.Collectors;

class PaymentJsonRepositoryImpl
        extends GenericJsonRepository<Payment>
        implements PaymentRepository {

    public PaymentJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.PAYMENTS.getPath(), TypeToken
                .getParameterized(Set.class, Payment.class)
                .getType());
    }

    @Override
    public Set<Payment> findAllByOrderID(Order orderID) {
        return entities.stream().filter(l -> l.getOrderID().equals(orderID))
                .collect(Collectors.toSet());
    }
}
