package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.controller.StoreController;
import de.storefinder.LocalStoreFinder.mapper.testfactories.EntityModelTestFactory;
import de.storefinder.LocalStoreFinder.mapper.testfactories.InputModelTestFactory;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.models.responses.PutOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import de.storefinder.LocalStoreFinder.services.StoreInputValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest {
    StoreController storeController;

    @Mock
    StoreRepository storeRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OpeningTimeRepository openingTimeRepository;

    @Mock
    OpeningTimesRepository openingTimesRepository;

    @Mock
    StoreCategoryRepository storeCategoryRepository;

    @Mock
    StoreMapper storeMapper;

    @BeforeEach
    void setup() {
        storeController = new StoreController();
        storeController.setStoreRepository(storeRepository);
        storeController.setAddressRepository(addressRepository);
        storeController.setPaymentRepository(paymentRepository);
        storeController.setOpeningTimeRepository(openingTimeRepository);
        storeController.setOpeningTimesRepository(openingTimesRepository);
        storeController.setStoreInputValidationService(new StoreInputValidationService());
        storeController.setStoreCategoryRepository(storeCategoryRepository);
        storeController.setStoreMapper(storeMapper);
    }

    @Test
    void createStore_ShouldReturn_BadRequest_AndNotSaveAnyDataInEntity_If_ValidationFailed() {
        StoreInputModel mockStoreInputModel = StoreInputModel.builder().build();

        ResponseEntity<PutOutputModel<String>> result = storeController.createStore(mockStoreInputModel);

        verify(storeRepository, never()).save(any());

        assertThat(Objects.requireNonNull(result.getBody()).getMessage()).isEqualTo("Request Data not correct");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void createStore_ShouldReturn_OK_AndSaveInputInEntity_If_ValidationSucceed() {
        StoreInputModel mockStoreInputModel = InputModelTestFactory.getStoreInputModelValid();
        Mockito.when(storeMapper.mapToEntity(any())).thenReturn(EntityModelTestFactory.getStore());

        ResponseEntity<PutOutputModel<String>> result = storeController.createStore(mockStoreInputModel);

        verify(storeMapper).mapToEntity(any());

        assertThat(Objects.requireNonNull(result.getBody()).getMessage()).isEqualTo("Store successfully added");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
