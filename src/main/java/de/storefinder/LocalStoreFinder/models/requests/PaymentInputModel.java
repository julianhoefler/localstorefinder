package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInputModel {

    private boolean cash;

    private boolean creditcard;

    private boolean payback;
}
