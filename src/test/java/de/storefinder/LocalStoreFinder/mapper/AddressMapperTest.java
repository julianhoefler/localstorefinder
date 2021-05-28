package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.mapper.testfactories.ServiceModelTestFactory;
import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.responses.AddressOutputModel;
import de.storefinder.LocalStoreFinder.services.GeoDataApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressMapperTest {

    @Mock
    GeoDataApiService geoDataApiService;

    @BeforeEach
    void setup() {
        AddressMapper.geoDataApiService = geoDataApiService;
        when(geoDataApiService.getGeoDataFromAddress(any())).thenReturn(ServiceModelTestFactory.getTestGeoData(12.0, 10.0));
    }

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
                .lat(12.0)
                .lng(10.0)
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
                .lng(10.0)
                .lat(12.0)
                .build();

        AddressOutputModel expected = AddressOutputModel.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .lng(10.0)
                .lat(12.0)
                .build();

        AddressOutputModel result = AddressMapper.mapToResponse(mockAddress);

        assertThat(result).isEqualTo(expected);
    }
}
