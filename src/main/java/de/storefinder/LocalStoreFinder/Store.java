package de.storefinder.LocalStoreFinder;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Store {

    @Id
    private String id;

    private int zip;

    private String name;

    private String address;
}
