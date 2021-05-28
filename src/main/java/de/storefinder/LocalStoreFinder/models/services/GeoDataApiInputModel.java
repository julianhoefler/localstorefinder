package de.storefinder.LocalStoreFinder.models.services;

import lombok.Data;

import java.util.ArrayList;

@Data
public class GeoDataApiInputModel {
    private ArrayList<ApiResults> results;
}
