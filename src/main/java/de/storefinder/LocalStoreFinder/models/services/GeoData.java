package de.storefinder.LocalStoreFinder.models.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeoData {
    private double lat;
    private double lng;
}
