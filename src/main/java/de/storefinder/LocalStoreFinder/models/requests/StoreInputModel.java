package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StoreInputModel {

    private String name;

    private AddressInputModel address;

    private String preImage;

    private String description;

    private OpeningTimesInputModel openingTimes;

    private PaymentInputModel payment;

    private ArrayList<String> categories;
}
