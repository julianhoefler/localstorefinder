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
public class Payment {

    public Payment() {
    }

    @Id
    private String id;

    private boolean cash;

    private boolean creditcard;

    private boolean payback;
}
