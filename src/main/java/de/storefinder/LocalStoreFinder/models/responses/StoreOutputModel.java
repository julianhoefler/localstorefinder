package de.storefinder.LocalStoreFinder.models.responses;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import de.storefinder.LocalStoreFinder.models.entities.Payment;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.requests.OpeningTimesInputModel;
import de.storefinder.LocalStoreFinder.models.requests.PaymentInputModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreOutputModel {

    private String id;

    private String name;

    private Address address;

    private String preImage;

    private String description;

    private OpeningTimesOutputModel openingTimes;

    private Payment payment;
}
