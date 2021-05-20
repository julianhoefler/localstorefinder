package de.storefinder.LocalStoreFinder.repositories;

import de.storefinder.LocalStoreFinder.models.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
