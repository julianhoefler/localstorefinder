package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.StoreCategory;
import de.storefinder.LocalStoreFinder.models.requests.*;

import java.util.ArrayList;

public class TestFactory {
    public static StoreInputModel getTestStoreInputModelValid() {
        return StoreInputModel.builder()
                .address(getTestAddressInputModel())
                .payment(getTestPaymentInputModel())
                .openingTimes(getTestOpeningTimesInputModel())
                .name("Name")
                .description("Beschreibung")
                .preImage("Image")
                .categories(new ArrayList<>())
                .build();
    }

    public static AddressInputModel getTestAddressInputModel() {
        return AddressInputModel.builder()
                .city("Frankfurt")
                .zip("60323")
                .street("Teststreet")
                .number(10)
                .build();
    }

    public static PaymentInputModel getTestPaymentInputModel() {
        return PaymentInputModel.builder()
                .payback(true)
                .creditcard(true)
                .cash(true)
                .build();
    }

    public static OpeningTimesInputModel getTestOpeningTimesInputModel() {
        return OpeningTimesInputModel.builder()
                .monday(getTestOpeningTimeInputModel())
                .tuesday(getTestOpeningTimeInputModel())
                .wednesday(getTestOpeningTimeInputModel())
                .thursday(getTestOpeningTimeInputModel())
                .friday(getTestOpeningTimeInputModel())
                .saturday(getTestOpeningTimeInputModel())
                .sunday(getTestOpeningTimeInputModel())
                .build();
    }

    public static OpeningTimeInputModel getTestOpeningTimeInputModel() {
        return OpeningTimeInputModel.builder()
                .isOpen(true)
                .openingTime("10:30")
                .closingTime("14:30")
                .build();
    }

    public static ArrayList<StoreCategory> getArrayListStoreCategory() {
        ArrayList<StoreCategory> storeCategoryArrayList = new ArrayList<>();

        storeCategoryArrayList.add(StoreCategory.builder()
                .id("1234")
                .categoryId("2345")
                .storeId("3456")
                .build());
        storeCategoryArrayList.add(StoreCategory.builder()
                .id("4567")
                .categoryId("5678")
                .storeId("6789")
                .build());

        return storeCategoryArrayList;
    }
}
