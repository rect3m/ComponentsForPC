package com.rect2m.componentsforpc.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.repository.contracts.OrderRepository;
import java.util.Set;
import java.util.stream.Collectors;

class OrderJsonRepositoryImpl
        extends GenericJsonRepository<Order>
        implements OrderRepository {

    public OrderJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.ORDERS.getPath(), TypeToken
                .getParameterized(Set.class, Order.class)
                .getType());
    }

    @Override
    public Set<Order> findAllByLocalDate(Order purchaseDateTime) {
        return entities.stream().filter(c -> c.getPurchaseDateTime().equals(purchaseDateTime))
                .collect(Collectors.toSet());
    }
}
