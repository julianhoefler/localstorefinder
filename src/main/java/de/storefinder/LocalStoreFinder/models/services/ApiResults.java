package de.storefinder.LocalStoreFinder.models.services;

import lombok.Data;

@Data
public class ApiResults {
    public ApiResults() {}
    private GeoData geometry;
}
