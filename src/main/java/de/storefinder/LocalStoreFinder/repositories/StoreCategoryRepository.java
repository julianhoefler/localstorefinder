package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.StoreCategory;
import org.springframework.data.repository.CrudRepository;

public interface StoreCategoryRepository extends CrudRepository<StoreCategory, String> {
}
