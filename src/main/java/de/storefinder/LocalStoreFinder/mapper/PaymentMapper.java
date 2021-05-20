package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Payment;
import de.storefinder.LocalStoreFinder.models.requests.PaymentInputModel;

import java.util.UUID;

public class PaymentMapper {

    public static Payment mapToEntity(PaymentInputModel paymentInputModel) {
        String uuid = UUID.randomUUID().toString();

        return Payment.builder()
                .id(uuid)
                .cash(paymentInputModel.isCash())
                .creditcard(paymentInputModel.isCreditcard())
                .payback(paymentInputModel.isPayback())
                .build();
    }
}
