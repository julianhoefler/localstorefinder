package de.storefinder.LocalStoreFinder.mapper;

import de.storefinder.LocalStoreFinder.models.entities.Category;
import de.storefinder.LocalStoreFinder.models.requests.CategoryInputModel;
import de.storefinder.LocalStoreFinder.models.responses.CategoryOutputModel;
import de.storefinder.LocalStoreFinder.repositories.CategoryRepository;

import java.util.Optional;
import java.util.UUID;

public class CategoryMapper {
    public static Category mapToEntity(CategoryInputModel categoryInputModel) {
        String uuid = UUID.randomUUID().toString();

        return Category.builder()
                .id(uuid)
                .label(categoryInputModel.getLabel())
                .description(categoryInputModel.getDescription())
                .colorCode(categoryInputModel.getColorCode())
                .build();
    }

    public static CategoryOutputModel mapToResponse(String categoryId, CategoryRepository categoryRepository) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return CategoryOutputModel.builder()
                    .label(category.getLabel())
                    .description(category.getDescription())
                    .colorCode(category.getColorCode())
                    .build();
        }
        return null;
    }
}
