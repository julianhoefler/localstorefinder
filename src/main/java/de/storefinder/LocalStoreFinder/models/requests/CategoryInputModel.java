package de.storefinder.LocalStoreFinder.models.requests;

import lombok.Data;

@Data
public class CategoryInputModel {
    private String label;
    private String description;
    private String colorCode;
}
