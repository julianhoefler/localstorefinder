package de.storefinder.LocalStoreFinder.services;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.services.GeoData;
import de.storefinder.LocalStoreFinder.models.services.GeoDataApiInputModel;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@Setter
public class GeoDataApiService {
    private final String APIKEY = "6bb1d24466394e7fb59f3d190ef928dc";
    private RestTemplate restTemplate;

    public GeoDataApiService() {
        restTemplate = new RestTemplate();
    }

    public GeoData getGeoDataFromAddress(AddressInputModel address) {
        String querySearchString = address.getZip() + " " + address.getCity() + " " + address.getStreet() + " " + address.getNumber() + " DE";
        ResponseEntity<GeoDataApiInputModel> geoDataApiInputModel = restTemplate.getForEntity("https://api.opencagedata.com/geocode/v1/json?key=" + APIKEY + "&q=" + querySearchString + "&pretty=1&no_annotations=1", GeoDataApiInputModel.class);

        if (geoDataApiInputModel.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(geoDataApiInputModel.getBody()).getResults().get(0).getGeometry();
        }
        return null;
    }

    public GeoData getGeoDataFromZip(String zip) {
        String querySearchString = zip + " DE";
        ResponseEntity<GeoDataApiInputModel> geoDataApiInputModel = restTemplate.getForEntity("https://api.opencagedata.com/geocode/v1/json?key=" + APIKEY + "&q=" + querySearchString + "&pretty=1&no_annotations=1", GeoDataApiInputModel.class);

        if (geoDataApiInputModel.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(geoDataApiInputModel.getBody()).getResults().get(0).getGeometry();
        }
        return null;
    }
}
