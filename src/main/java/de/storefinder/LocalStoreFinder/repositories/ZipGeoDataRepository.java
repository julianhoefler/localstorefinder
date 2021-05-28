package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.ZipGeoData;
import org.springframework.data.repository.CrudRepository;

public interface ZipGeoDataRepository extends CrudRepository<ZipGeoData, String> {
}
