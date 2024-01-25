package com.rect2m.componentsforpc.domain.impl;

import com.rect2m.componentsforpc.domain.contract.OrderService;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.repository.contracts.OrderRepository;
import java.time.LocalDate;
import java.util.Set;

public class OrderServiceImpl
        extends GenericService<Order>
        implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> findAllByLocalDate(LocalDate purchaseDate) {
        return orderRepository.findAllByLocalDate(purchaseDate);
    }
}