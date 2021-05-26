package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInputModel {

    private String zip;

    private String city;

    private String street;

    private int number;
}
