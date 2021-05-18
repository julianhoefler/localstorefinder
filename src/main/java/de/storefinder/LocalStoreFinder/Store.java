package de.storefinder.LocalStoreFinder;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Store {

    public Store(String id, int zip, String name, String address) {
        this.id = id;
        this.zip = zip;
        this.name = name;
        this.address = address;
    }

    public Store() {
    }

    @Id
    private String id;

    private int zip;

    private String name;

    private String address;
}
