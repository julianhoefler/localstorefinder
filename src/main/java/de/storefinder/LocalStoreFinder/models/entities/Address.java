package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Address {

    public Address() {
    }

    @Id
    private String id;

    private String zip;

    private String city;

    private String street;

    private int number;
}
