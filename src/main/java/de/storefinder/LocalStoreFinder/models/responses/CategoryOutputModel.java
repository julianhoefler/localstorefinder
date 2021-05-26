package de.storefinder.LocalStoreFinder.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryOutputModel {
    private String label;

    private String colorCode;

    private String description;
}
