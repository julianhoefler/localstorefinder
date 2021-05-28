package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.mapper.testfactories.EntityModelTestFactory;
import de.storefinder.LocalStoreFinder.mapper.testfactories.OutputModelTestFactory;
import de.storefinder.LocalStoreFinder.mapper.testfactories.ServiceModelTestFactory;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.ZipGeoDataRepository;
import de.storefinder.LocalStoreFinder.services.FilterService;
import de.storefinder.LocalStoreFinder.services.GeoDataApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {
    FilterService filterService;

    @Mock
    private ZipGeoDataRepository zipGeoDataRepository;

    @Mock
    private GeoDataApiService geoDataApiService;

    @BeforeEach
    void setup() {
        filterService = new FilterService(zipGeoDataRepository, geoDataApiService);
    }

    @Test
    void checkFilter_ShouldReturnTrue_IfZipInSurroundingArea() {
        when(zipGeoDataRepository.findById(any())).thenReturn(Optional.of(EntityModelTestFactory.getTestZipGeoData(12.0, 10.0)));
        String zip = "60323";
        Integer umkreis = 10;

        //Hat eine Position von lat=12.0, lng=10.0
        StoreOutputModel storeOutputModel = OutputModelTestFactory.getStoreOutputModel();

        boolean filter = filterService.checkFilter(zip, umkreis, storeOutputModel);

        assertThat(filter).isTrue();
    }

    @Test
    void checkFilter_ShouldReturnFalse_IfZipIsNotInSurroundingArea() {
        when(zipGeoDataRepository.findById(any())).thenReturn(Optional.of(EntityModelTestFactory.getTestZipGeoData(10.0, 14.0)));
        String zip = "60323";
        Integer umkreis = 10;

        //Hat eine Position von lat=12.0, lng=10.0
        StoreOutputModel storeOutputModel = OutputModelTestFactory.getStoreOutputModel();

        boolean filter = filterService.checkFilter(zip, umkreis, storeOutputModel);

        assertThat(filter).isFalse();
    }

    @Test
    void checkFilter_ShouldGetDataFromApiAndSaveItToRepo_IfPosCantFindInRepository() {
        when(zipGeoDataRepository.findById(any())).thenReturn(Optional.empty());
        when(geoDataApiService.getGeoDataFromZip(any())).thenReturn(ServiceModelTestFactory.getTestGeoData(12.0, 10.0));
        String zip = "60323";
        Integer umkreis = 10;

        //Hat eine Position von lat=12.0, lng=10.0
        StoreOutputModel storeOutputModel = OutputModelTestFactory.getStoreOutputModel();

        boolean filter = filterService.checkFilter(zip, umkreis, storeOutputModel);

        verify(geoDataApiService).getGeoDataFromZip(eq("60323"));
        verify(zipGeoDataRepository).save(any());
        assertThat(filter).isTrue();
    }
}
