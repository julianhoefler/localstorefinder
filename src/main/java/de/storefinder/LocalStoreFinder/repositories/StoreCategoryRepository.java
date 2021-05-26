package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.StoreCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StoreCategoryRepository extends CrudRepository<StoreCategory, String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM StoreCategory WHERE categoryId = ?1")
    void deleteAllByCategoryId(String categoryID);

    @Modifying
    @Transactional
    @Query("DELETE FROM StoreCategory WHERE storeId = ?1")
    void deleteAllByStoreId(String storeID);
}
