package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.responses.AddressOutputModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class AddressMapperTest {

    @Test
    void mapToEntity_ShouldReturnAddressEntity_WithRandomUUID() {
        AddressInputModel addressInputModel = AddressInputModel.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();

        Address expected = Address.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();

        Address result = AddressMapper.mapToEntity(addressInputModel);

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);

        assertThatCode(() -> {
            UUID.fromString(result.getId());
        }).doesNotThrowAnyException();
    }

    @Test
    void mapToResponse_ShouldReturnOutputModel() {
        Address mockAddress = Address.builder()
                .id("random-uuid")
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();

        AddressOutputModel expected = AddressOutputModel.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();

        AddressOutputModel result = AddressMapper.mapToResponse(mockAddress);

        assertThat(result).isEqualTo(expected);
    }
}
