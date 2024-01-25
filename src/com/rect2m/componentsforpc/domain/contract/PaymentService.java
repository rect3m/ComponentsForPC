package com.rect2m.componentsforpc.domain.contract;

import com.rect2m.componentsforpc.domain.Service;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.entity.impl.Payment;
import java.util.Set;

public interface PaymentService extends Service<Payment> {

    Set<Payment> findAllByOrderID(Order orderID);
}
