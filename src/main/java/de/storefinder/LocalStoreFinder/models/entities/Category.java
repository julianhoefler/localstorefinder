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
public class Category {

    public Category() {
    }

    @Id
    private String id;

    private String label;

    private String colorCode;

    private String description;
}
