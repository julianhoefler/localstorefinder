package de.storefinder.LocalStoreFinder.mapper.testfactories;

import de.storefinder.LocalStoreFinder.models.entities.*;

import java.util.ArrayList;

public class EntityModelTestFactory {

    public static Address getEmptyAddress() {
        return Address.builder().build();
    }

    public static Payment getEmptyPayment() {
        return Payment.builder().build();
    }

    public static OpeningTimes getEmptyOpeningTimes() {
        return OpeningTimes.builder().build();
    }

    public static OpeningTime getEmptyOpeningTime() {
        return OpeningTime.builder().build();
    }

    public static Store getStore() {
        return Store.builder()
                .id("id")
                .name("name")
                .description("description")
                .categories(new ArrayList<>())
                .address("address")
                .payment("payment")
                .openingTimes("opening_Times")
                .isActive(true)
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

    public static ZipGeoData getTestZipGeoData(double lat, double lng) {
        return ZipGeoData.builder()
                .zip("60323")
                .lng(lng)
                .lat(lat)
                .build();
    }
}
