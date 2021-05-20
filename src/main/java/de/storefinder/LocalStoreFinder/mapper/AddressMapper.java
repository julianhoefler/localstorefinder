package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;

import java.util.UUID;

public class AddressMapper {

    public static Address mapToEntity(AddressInputModel addressInputModel) {
        String uuid = UUID.randomUUID().toString();

        return Address.builder()
                .id(uuid)
                .city(addressInputModel.getCity())
                .number(addressInputModel.getNumber())
                .street(addressInputModel.getStreet())
                .zip(addressInputModel.getZip())
                .build();
    }
}
