package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.controller.StoreController;
import de.storefinder.LocalStoreFinder.mapper.testfactories.InputModelTestFactory;
import de.storefinder.LocalStoreFinder.models.requests.StoreInputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import de.storefinder.LocalStoreFinder.services.StoreInputValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @BeforeEach
    void setup() {
        storeController = new StoreController();
        storeController.setStoreRepository(storeRepository);
        storeController.setAddressRepository(addressRepository);
        storeController.setPaymentRepository(paymentRepository);
        storeController.setOpeningTimeRepository(openingTimeRepository);
        storeController.setOpeningTimesRepository(openingTimesRepository);
        storeController.setStoreInputValidationService(new StoreInputValidationService());
    }

    @Test
    void createStore_ShouldReturn_BadRequest_AndNotSaveAnyDataInEntity_If_ValidationFailed() {
        StoreInputModel mockStoreInputModel = StoreInputModel.builder().build();

        ResponseEntity<String> result = storeController.createStore(mockStoreInputModel);

        verify(storeRepository, never()).save(any());

        assertThat(result.getBody()).isEqualTo("Request Data not correct");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void createStore_ShouldReturn_OK_AndSaveInputInEntity_If_ValidationSucceed() {
        StoreInputModel mockStoreInputModel = InputModelTestFactory.getStoreInputModelValid();

        ResponseEntity result = storeController.createStore(mockStoreInputModel);

        verify(addressRepository).save(any());
        verify(paymentRepository).save(any());
        verify(openingTimeRepository, times(7)).save(any());
        verify(openingTimesRepository).save(any());
        verify(storeRepository).save(any());

        assertThat(result.getBody()).isEqualTo("Successfully added to database.");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
