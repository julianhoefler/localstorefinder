package de.storefinder.LocalStoreFinder.services;

import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import de.storefinder.LocalStoreFinder.models.entities.ZipGeoData;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.models.services.GeoData;
import de.storefinder.LocalStoreFinder.repositories.ZipGeoDataRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FilterService {
    private ZipGeoDataRepository zipGeoDataRepository;

    public boolean checkFilter(String zip, Integer umkreis, StoreOutputModel storeOutputModel) {
        GeoData zipGeodata = getZipGeoData(zip);
        GeoData addressGeodata = new GeoData(storeOutputModel.getAddress().getLat(), storeOutputModel.getAddress().getLng());
        double distance = calculateDistance(addressGeodata, zipGeodata);

        return !(umkreis < distance);
    }

    private double calculateDistance(GeoData geoData1, GeoData geoData2) {
        Coordinate lat1 = Coordinate.fromDegrees(geoData1.getLat());
        Coordinate lng1 = Coordinate.fromDegrees(geoData1.getLng());
        Coordinate lat2 = Coordinate.fromDegrees(geoData2.getLat());
        Coordinate lng2 = Coordinate.fromDegrees(geoData2.getLng());
        Point point1 = Point.at(lat1, lng1);
        Point point2 = Point.at(lat2, lng2);

        return EarthCalc.gcd.distance(point1, point2) / 1000;
    }

    private GeoData getZipGeoData(String zip) {
        Optional<ZipGeoData> zipGeoData = zipGeoDataRepository.findById(zip);

        if (zipGeoData.isPresent()) {
            GeoData geoData = new GeoData();
            geoData.setLat(zipGeoData.get().getLat());
            geoData.setLng(zipGeoData.get().getLng());

            return geoData;
        } else {
            GeoData geodata = new GeoDataApiService().getGeoDataFromZip(zip);
            ZipGeoData saveZipGeoData = ZipGeoData.builder()
                    .zip(zip)
                    .lat(geodata.getLat())
                    .lng(geodata.getLng())
                    .build();
            zipGeoDataRepository.save(saveZipGeoData);

            return geodata;
        }
    }
}
