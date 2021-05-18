package de.storefinder.LocalStoreFinder;

import nonapi.io.github.classgraph.json.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class Store {

    @javax.persistence.Id
    @Id
    @GeneratedValue
    private Integer id;

    private int zip;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
