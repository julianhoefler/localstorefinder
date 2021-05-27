package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.mapper.testfactories.OutputModelTestFactory;
import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.responses.AddressOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.OpeningTimesOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.PaymentOutputModel;
import de.storefinder.LocalStoreFinder.models.responses.StoreOutputModel;
import de.storefinder.LocalStoreFinder.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class StoreMapperTest {

    private StoreMapper storeMapper;

    private final AddressRepository addressRepositoryMock = mock(AddressRepository.class);
    private final PaymentRepository paymentRepositoryMock = mock(PaymentRepository.class);
    private final OpeningTimesRepository openingTimesRepositoryMock = mock(OpeningTimesRepository.class);
    private final OpeningTimeRepository openingTimeRepositoryMock = mock(OpeningTimeRepository.class);
    private final CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
    private final StoreCategoryRepository storeCategoryRepositoryMock = mock(StoreCategoryRepository.class);

    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String PRE_IMAGE = "preImage";

    private final String ADDRESS = "address";
    private final String OPENING_TIMES = "opening_times";
    private final String PAYMENT = "payment";

    private final AddressOutputModel ADDRESS_OBJECT = AddressOutputModel.builder().build();
    private final PaymentOutputModel PAYMENT_OBJECT = PaymentOutputModel.builder().build();
    private final OpeningTimesOutputModel OPENING_TIMES_OBJECT = OutputModelTestFactory.getEmptyOpeningTimesOutputModel();

    @BeforeEach
    void setUp() {
        storeMapper = new StoreMapper(
                addressRepositoryMock,
                paymentRepositoryMock,
                openingTimesRepositoryMock,
                openingTimeRepositoryMock,
                categoryRepositoryMock,
                storeCategoryRepositoryMock);
    }

    @Test
    void mapToResponse_shouldReturnExpectedStoreOutputModel() {
        Mockito.when(addressRepositoryMock.findById(any())).thenReturn(Optional.of(Address.builder().build()));
        Mockito.when(openingTimesRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTimes.builder().build()));
        Mockito.when(openingTimeRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTime.builder().build()));
        Mockito.when(paymentRepositoryMock.findById(any())).thenReturn(Optional.of(Payment.builder().build()));
        Store store = Store.builder()
                .name(NAME)
                .id(ID)
                .preImage(PRE_IMAGE)
                .openingTimes(OPENING_TIMES)
                .payment(PAYMENT)
                .description(DESCRIPTION)
                .address(ADDRESS)
                .categories(new ArrayList<>())
                .build();

        StoreOutputModel expected = StoreOutputModel.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .openingTimes(OPENING_TIMES_OBJECT)
                .preImage(PRE_IMAGE)
                .address(ADDRESS_OBJECT)
                .payment(PAYMENT_OBJECT)
                .categories(new ArrayList<>())
                .build();

        StoreOutputModel result = storeMapper.mapToResponse(store);

        assertEquals(expected, result);
    }

    @Test
    void mapToResponse_shouldReturnNull_whenAddressIsNotPresent() {
        Mockito.when(addressRepositoryMock.findById(any())).thenReturn(Optional.empty());
        Mockito.when(openingTimesRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTimes.builder().build()));
        Mockito.when(openingTimeRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTime.builder().build()));
        Mockito.when(paymentRepositoryMock.findById(any())).thenReturn(Optional.of(Payment.builder().build()));
        Store store = Store.builder()
                .name(NAME)
                .id(ID)
                .preImage(PRE_IMAGE)
                .openingTimes(OPENING_TIMES)
                .payment(PAYMENT)
                .description(DESCRIPTION)
                .address(ADDRESS)
                .categories(new ArrayList<>())
                .build();

        StoreOutputModel result = storeMapper.mapToResponse(store);

        assertNull(result);
    }

    @Test
    void mapToResponse_shouldReturnNull_whenOpeningTimesIsNotPresent() {
        Mockito.when(openingTimesRepositoryMock.findById(any())).thenReturn(Optional.empty());
        Mockito.when(addressRepositoryMock.findById(any())).thenReturn(Optional.of(Address.builder().build()));
        Mockito.when(openingTimeRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTime.builder().build()));
        Mockito.when(paymentRepositoryMock.findById(any())).thenReturn(Optional.of(Payment.builder().build()));
        Store store = Store.builder()
                .name(NAME)
                .id(ID)
                .preImage(PRE_IMAGE)
                .openingTimes(OPENING_TIMES)
                .payment(PAYMENT)
                .description(DESCRIPTION)
                .address(ADDRESS)
                .categories(new ArrayList<>())
                .build();

        StoreOutputModel result = storeMapper.mapToResponse(store);

        assertNull(result);
    }

    @Test
    void mapToResponse_shouldReturnNull_whenPaymentIsNotPresent() {
        Mockito.when(paymentRepositoryMock.findById(any())).thenReturn(Optional.empty());
        Mockito.when(addressRepositoryMock.findById(any())).thenReturn(Optional.of(Address.builder().build()));
        Mockito.when(openingTimeRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTime.builder().build()));
        Mockito.when(openingTimesRepositoryMock.findById(any())).thenReturn(Optional.of(OpeningTimes.builder().build()));
        Store store = Store.builder()
                .name(NAME)
                .id(ID)
                .preImage(PRE_IMAGE)
                .openingTimes(OPENING_TIMES)
                .payment(PAYMENT)
                .description(DESCRIPTION)
                .address(ADDRESS)
                .categories(new ArrayList<>())
                .build();

        StoreOutputModel result = storeMapper.mapToResponse(store);

        assertNull(result);
    }
}