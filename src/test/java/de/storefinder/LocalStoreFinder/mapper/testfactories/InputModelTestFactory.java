package de.storefinder.LocalStoreFinder.mapper.testfactories;

import de.storefinder.LocalStoreFinder.models.requests.*;

import java.util.ArrayList;

public class InputModelTestFactory {

    public static StoreInputModel getStoreInputModelValid() {
        return StoreInputModel.builder()
                .address(getAddressInputModel())
                .payment(getPaymentInputModel())
                .openingTimes(getOpeningTimesInputModel())
                .name("Name")
                .description("Beschreibung")
                .preImage("Image")
                .categories(new ArrayList<>())
                .build();
    }

    public static AddressInputModel getAddressInputModel() {
        return AddressInputModel.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();
    }

    public static PaymentInputModel getPaymentInputModel() {
        return PaymentInputModel.builder()
                .payback(true)
                .creditcard(true)
                .cash(true)
                .build();
    }

    public static OpeningTimesInputModel getOpeningTimesInputModel() {
        return OpeningTimesInputModel.builder()
                .monday(getOpeningTimeInputModel())
                .tuesday(getOpeningTimeInputModel())
                .wednesday(getOpeningTimeInputModel())
                .thursday(getOpeningTimeInputModel())
                .friday(getOpeningTimeInputModel())
                .saturday(getOpeningTimeInputModel())
                .sunday(getOpeningTimeInputModel())
                .build();
    }

    public static OpeningTimeInputModel getOpeningTimeInputModel() {
        return OpeningTimeInputModel.builder()
                .isOpen(true)
                .openingTime("10:30")
                .closingTime("14:30")
                .build();
    }
}
