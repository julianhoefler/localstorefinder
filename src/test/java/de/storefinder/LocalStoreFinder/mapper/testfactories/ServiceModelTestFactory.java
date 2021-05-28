package de.storefinder.LocalStoreFinder.mapper.testfactories;

import de.storefinder.LocalStoreFinder.models.services.ApiResults;
import de.storefinder.LocalStoreFinder.models.services.GeoData;
import de.storefinder.LocalStoreFinder.models.services.GeoDataApiInputModel;

import java.util.ArrayList;

public class ServiceModelTestFactory {
    public static GeoData getTestGeoData(double lat, double lon) {
        GeoData geoData = new GeoData();
        geoData.setLat(lat);
        geoData.setLng(lon);
        return geoData;
    }

    public static GeoDataApiInputModel getTestGeoDataApiInputModel(double lat, double lon) {
        ArrayList<ApiResults> apiResults = new ArrayList<>();
        ApiResults apiResults1 = new ApiResults();
        apiResults1.setGeometry(getTestGeoData(lat, lon));
        apiResults.add(apiResults1);
        GeoDataApiInputModel geoDataApiInputModel = new GeoDataApiInputModel();
        geoDataApiInputModel.setResults(apiResults);

        return geoDataApiInputModel;
    }
}
