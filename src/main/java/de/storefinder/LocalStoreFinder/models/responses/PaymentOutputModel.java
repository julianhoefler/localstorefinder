package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Id;

@Data
@Builder
public class PaymentOutputModel {

    private boolean cash;

    private boolean creditcard;

    private boolean payback;
}
