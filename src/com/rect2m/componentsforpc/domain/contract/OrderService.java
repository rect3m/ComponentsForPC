package com.rect2m.componentsforpc.domain.contract;

import com.rect2m.componentsforpc.domain.Service;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import java.time.LocalDate;
import java.util.Set;

public interface OrderService extends Service<Order> {

    Set<Order> findAllByLocalDate(LocalDate purchaseDateTime);
}