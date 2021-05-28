package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressOutputModel {
    private String zip;

    private String city;

    private String street;

    private int number;

    private double lat;

    private double lng;
}
