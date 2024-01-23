package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.model.entity.Category;
import com.passboard.PaymentManagement.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories/v1")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody String type) {
        if (!categoryRepository.existsByType(type.toLowerCase()))
            return ResponseEntity.ok(categoryRepository.save(new Category(type.toLowerCase())));
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
