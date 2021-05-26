package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTime;
import org.springframework.data.repository.CrudRepository;

public interface OpeningTimeRepository extends CrudRepository<OpeningTime, String> {
}
