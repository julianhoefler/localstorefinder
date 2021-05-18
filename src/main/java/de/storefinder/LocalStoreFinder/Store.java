package de.storefinder.LocalStoreFinder;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Store {

    public Store() {
    }

    @Id
    private String id;

    private int zip;

    private String name;

    private String address;
}
