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
public class StoreCategory {

    public StoreCategory() {
    }

    @Id
    private String id;

    private String storeId;

    private String categoryId;
}
