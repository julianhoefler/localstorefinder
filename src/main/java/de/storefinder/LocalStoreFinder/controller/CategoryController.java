package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.CategoryMapper;
import de.storefinder.LocalStoreFinder.models.entities.Category;
import de.storefinder.LocalStoreFinder.models.requests.CategoryInputModel;
import de.storefinder.LocalStoreFinder.models.responses.PutOutputModel;
import de.storefinder.LocalStoreFinder.repositories.CategoryRepository;
import de.storefinder.LocalStoreFinder.repositories.StoreCategoryRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @ApiResponse(responseCode = "200", description = "Diese Schnittstelle gibt alle vorhandenen Kategorien aus",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Category.class)))
    )
    @GetMapping("/categories")
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Diese Schnittstelle erstellt eine neue Kategorie")
    @PutMapping("/categories")
    public ResponseEntity<PutOutputModel<String>> createCategorie(@RequestBody CategoryInputModel categoryInputModel) {
        Category category = CategoryMapper.mapToEntity(categoryInputModel);
        categoryRepository.save(category);

        PutOutputModel<String> response = PutOutputModel.<String>builder()
                .id(category.getId())
                .message("Category successfully added")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "204", description = "Die Kategorie wurde gelöscht")
    @DeleteMapping("/categories/{uuid}")
    public ResponseEntity<?> deleteCategorie(@PathVariable String uuid) {
        categoryRepository.deleteById(uuid);
        storeCategoryRepository.deleteAllByCategoryId(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
