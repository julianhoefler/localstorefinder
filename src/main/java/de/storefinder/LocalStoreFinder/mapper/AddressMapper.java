package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.responses.AddressOutputModel;

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

    public static AddressOutputModel mapToResponse(Address addressEntity) {
        return AddressOutputModel.builder()
                .city(addressEntity.getCity())
                .number(addressEntity.getNumber())
                .street(addressEntity.getStreet())
                .zip(addressEntity.getZip())
                .build();
    }
}
