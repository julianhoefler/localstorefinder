package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.OpeningTimes;
import org.springframework.data.repository.CrudRepository;

public interface OpeningTimesRepository extends CrudRepository<OpeningTimes, String> {
}
