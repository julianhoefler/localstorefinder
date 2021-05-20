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
public class Store {

    public Store() {
    }

    @Id
    private String id;

    private String name;

    private String address;

    private String preImage;

    private String description;

    private String openingTimes;

    private String payment;

}
