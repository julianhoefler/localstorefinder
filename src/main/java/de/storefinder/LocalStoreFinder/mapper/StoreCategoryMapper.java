package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.StoreCategory;

import java.util.UUID;

public class StoreCategoryMapper {
    public static StoreCategory mapToEntity(String categoryId, String storeId) {
        String uuid = UUID.randomUUID().toString();

        return StoreCategory.builder()
                .id(uuid)
                .categoryId(categoryId)
                .storeId(storeId)
                .build();
    }
}
