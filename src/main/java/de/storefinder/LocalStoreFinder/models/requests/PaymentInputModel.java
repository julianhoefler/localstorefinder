package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

@Data
public class PaymentInputModel {

    private boolean cash;

    private boolean creditcard;

    private boolean payback;
}
