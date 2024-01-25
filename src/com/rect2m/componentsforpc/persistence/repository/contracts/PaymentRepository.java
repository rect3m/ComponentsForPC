package com.rect2m.componentsforpc.persistence.repository.contracts;

import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.entity.impl.Payment;
import com.rect2m.componentsforpc.persistence.repository.Repository;
import java.util.Set;

public interface PaymentRepository extends Repository<Payment> {

    Set<Payment> findAllByOrderID(Order orderID);
}
