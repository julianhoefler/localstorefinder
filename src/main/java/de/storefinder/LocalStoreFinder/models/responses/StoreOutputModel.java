package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreOutputModel {

    private String id;

    private String name;

    private AddressOutputModel address;

    private String preImage;

    private String description;

    private OpeningTimesOutputModel openingTimes;

    private PaymentOutputModel payment;

    private boolean isActive;

    private List<CategoryOutputModel> categories;
}
