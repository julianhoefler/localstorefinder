package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, String> {
}
