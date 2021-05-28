package de.storefinder.LocalStoreFinder.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@Builder
public class ZipGeoData {

    public ZipGeoData() {
    }

    @Id
    private String zip;

    private double lat;
    private double lng;
}
