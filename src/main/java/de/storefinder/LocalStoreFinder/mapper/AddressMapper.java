package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.responses.AddressOutputModel;
import de.storefinder.LocalStoreFinder.models.services.GeoData;
import de.storefinder.LocalStoreFinder.services.GeoDataApiService;
import lombok.Setter;

import java.util.UUID;

public class AddressMapper {

    public static GeoDataApiService geoDataApiService = new GeoDataApiService();

    public static Address mapToEntity(AddressInputModel addressInputModel) {
        GeoData geoData = geoDataApiService.getGeoDataFromAddress(addressInputModel);
        String uuid = UUID.randomUUID().toString();

        return Address.builder()
                .id(uuid)
                .city(addressInputModel.getCity())
                .number(addressInputModel.getNumber())
                .street(addressInputModel.getStreet())
                .zip(addressInputModel.getZip())
                .lat(geoData.getLat())
                .lng(geoData.getLng())
                .build();
    }

    public static AddressOutputModel mapToResponse(Address addressEntity) {
        return AddressOutputModel.builder()
                .city(addressEntity.getCity())
                .number(addressEntity.getNumber())
                .street(addressEntity.getStreet())
                .zip(addressEntity.getZip())
                .lat(addressEntity.getLat())
                .lng(addressEntity.getLng())
                .build();
    }
}
