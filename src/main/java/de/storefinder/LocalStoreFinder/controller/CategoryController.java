package de.storefinder.LocalStoreFinder.controller;

import de.storefinder.LocalStoreFinder.mapper.CategoryMapper;
import de.storefinder.LocalStoreFinder.models.entities.Category;
import de.storefinder.LocalStoreFinder.models.requests.CategoryInputModel;
import de.storefinder.LocalStoreFinder.repositories.CategoryRepository;
import de.storefinder.LocalStoreFinder.repositories.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("/categories")
    public ResponseEntity<?> createCategorie(@RequestBody CategoryInputModel categoryInputModel) {
        Category category = CategoryMapper.mapToEntity(categoryInputModel);
        categoryRepository.save(category);

        return new ResponseEntity<>("Category added", HttpStatus.OK);
    }
}
