package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

@Data
public class AddressInputModel {

    private String zip;

    private String city;

    private String street;

    private int number;
}
