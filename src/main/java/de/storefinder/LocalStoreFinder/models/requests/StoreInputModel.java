package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

@Data
public class StoreInputModel {

    private int zip;

    private String name;

    private String address;
}
