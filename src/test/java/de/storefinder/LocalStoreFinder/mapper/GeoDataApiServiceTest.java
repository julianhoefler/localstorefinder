package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.mapper.testfactories.EntityModelTestFactory;
import de.storefinder.LocalStoreFinder.mapper.testfactories.InputModelTestFactory;
import de.storefinder.LocalStoreFinder.mapper.testfactories.ServiceModelTestFactory;
import de.storefinder.LocalStoreFinder.models.entities.Address;
import de.storefinder.LocalStoreFinder.models.requests.AddressInputModel;
import de.storefinder.LocalStoreFinder.models.services.GeoData;
import de.storefinder.LocalStoreFinder.models.services.GeoDataApiInputModel;
import de.storefinder.LocalStoreFinder.services.GeoDataApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GeoDataApiServiceTest {
    @Mock
    private RestTemplate restTemplate;

    private GeoDataApiService geoDataApiService;

    @BeforeEach
    void setup() {
        when(restTemplate.getForEntity(anyString(), any(Class.class))).thenReturn(
                new ResponseEntity<>(ServiceModelTestFactory.getTestGeoDataApiInputModel(10.0, 20.0), HttpStatus.OK)
        );

        geoDataApiService = new GeoDataApiService();
        geoDataApiService.setRestTemplate(restTemplate);
    }

    @Test
    void getGeoDataFromAddress() {

        AddressInputModel address = InputModelTestFactory.getAddressInputModel();
        GeoData geoData = geoDataApiService.getGeoDataFromAddress(address);

        verify(restTemplate).getForEntity(eq("https://api.opencagedata.com/geocode/v1/json?key=6bb1d24466394e7fb59f3d190ef928dc&q=60323 Frankfurt Teststreet 10 DE&pretty=1&no_annotations=1"), any(Class.class));
        assertThat(geoData).isEqualTo(ServiceModelTestFactory.getTestGeoData(10.0, 20.0));
    }
}
