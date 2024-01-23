package com.passboard.PaymentManagement.service;


import com.passboard.PaymentManagement.model.entity.Category;
import com.passboard.PaymentManagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findOrPersist(List<String> types) {
        List<Category> result = new ArrayList<>();
        types.forEach(type -> categoryRepository.findByType(type.toLowerCase()).ifPresentOrElse(result::add, () -> result.add(categoryRepository.save(new Category(type.toLowerCase())))));
        return result;
    }
}
