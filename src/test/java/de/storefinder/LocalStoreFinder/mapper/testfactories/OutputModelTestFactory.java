package de.storefinder.LocalStoreFinder.mapper.testfactories;

import de.storefinder.LocalStoreFinder.models.responses.*;

import java.util.ArrayList;

public class OutputModelTestFactory {

    public static OpeningTimeOutputModel getEmptyOpeningTimeOutputModel() {
        return OpeningTimeOutputModel.builder().build();
    }

    public static OpeningTimesOutputModel getEmptyOpeningTimesOutputModel() {
        return OpeningTimesOutputModel.builder()
                .monday(getEmptyOpeningTimeOutputModel())
                .tuesday(getEmptyOpeningTimeOutputModel())
                .wednesday(getEmptyOpeningTimeOutputModel())
                .thursday(getEmptyOpeningTimeOutputModel())
                .friday(getEmptyOpeningTimeOutputModel())
                .saturday(getEmptyOpeningTimeOutputModel())
                .sunday(getEmptyOpeningTimeOutputModel())
                .build();
    }

    public static StoreOutputModel getStoreOutputModel() {
        return StoreOutputModel.builder()
                .id("id")
                .name("name")
                .description("description")
                .categories(new ArrayList<>())
                .address(getAddressOutputModel())
                .payment(getPaymentOutputModel())
                .openingTimes(getOpeningTimesOutputModel())
                .build();
    }

    public static OpeningTimeOutputModel getOpeningTimeOutputModel() {
        return OpeningTimeOutputModel.builder()
                .isOpen(true)
                .openingTime("10:30")
                .closingTime("17:30")
                .build();
    }

    public static OpeningTimesOutputModel getOpeningTimesOutputModel() {
        return OpeningTimesOutputModel.builder()
                .monday(getOpeningTimeOutputModel())
                .tuesday(getOpeningTimeOutputModel())
                .wednesday(getOpeningTimeOutputModel())
                .thursday(getOpeningTimeOutputModel())
                .friday(getOpeningTimeOutputModel())
                .saturday(getOpeningTimeOutputModel())
                .sunday(getOpeningTimeOutputModel())
                .build();
    }

    public static AddressOutputModel getAddressOutputModel() {
        return AddressOutputModel.builder()
                .city("city")
                .number(0)
                .street("street")
                .zip("zip")
                .lng(10.0)
                .lat(12.0)
                .build();
    }

    public static PaymentOutputModel getPaymentOutputModel() {
        return PaymentOutputModel.builder()
                .cash(true)
                .creditcard(true)
                .payback(true)
                .build();
    }
}
