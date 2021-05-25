package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.*;
import de.storefinder.LocalStoreFinder.models.responses.*;
import de.storefinder.LocalStoreFinder.repositories.AddressRepository;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimeRepository;
import de.storefinder.LocalStoreFinder.repositories.OpeningTimesRepository;
import de.storefinder.LocalStoreFinder.repositories.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String PRE_IMAGE = "preImage";

    private final String ADDRESS = "address";
    private final String OPENING_TIMES = "opening_times";
    private final String PAYMENT = "payment";

    private final AddressOutputModel ADDRESS_OBJECT = AddressOutputModel.builder().build();
    private final PaymentOutputModel PAYMENT_OBJECT = PaymentOutputModel.builder().build();
    private final OpeningTimeOutputModel OPENING_TIME_OBJECT = OpeningTimeOutputModel.builder().build();
    private final OpeningTimesOutputModel OPENING_TIMES_OBJECT = OpeningTimesOutputModel.builder()
            .monday(OPENING_TIME_OBJECT)
            .tuesday(OPENING_TIME_OBJECT)
            .wednesday(OPENING_TIME_OBJECT)
            .thursday(OPENING_TIME_OBJECT)
            .friday(OPENING_TIME_OBJECT)
            .saturday(OPENING_TIME_OBJECT)
            .sunday(OPENING_TIME_OBJECT)
            .build();

    @BeforeEach
    void setUp() {
        storeMapper = new StoreMapper(
                addressRepositoryMock,
                paymentRepositoryMock,
                openingTimesRepositoryMock,
                openingTimeRepositoryMock);
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
                .build();

        StoreOutputModel expected = StoreOutputModel.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .openingTimes(OPENING_TIMES_OBJECT)
                .preImage(PRE_IMAGE)
                .address(ADDRESS_OBJECT)
                .payment(PAYMENT_OBJECT)
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
                .build();

        StoreOutputModel result = storeMapper.mapToResponse(store);

        assertNull(result);
    }
}