package com.rect2m.componentsforpc.persistence.repository.contracts;

import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.repository.Repository;
import java.time.LocalDate;
import java.util.Set;

public interface OrderRepository extends Repository<Order> {

    Set<Order> findAllByLocalDate(LocalDate purchaseDateTime);
}
