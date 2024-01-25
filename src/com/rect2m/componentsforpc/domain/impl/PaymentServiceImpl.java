package com.rect2m.componentsforpc.domain.impl;

import com.rect2m.componentsforpc.domain.contract.PaymentService;
import com.rect2m.componentsforpc.persistence.entity.impl.Order;
import com.rect2m.componentsforpc.persistence.entity.impl.Payment;
import com.rect2m.componentsforpc.persistence.repository.contracts.PaymentRepository;
import java.util.Set;

public class PaymentServiceImpl
        extends GenericService<Payment>
        implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        super(paymentRepository);
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Set<Payment> findAllByOrderID(Order orderID) {
        return paymentRepository.findAllByOrderID(orderID);
    }
}