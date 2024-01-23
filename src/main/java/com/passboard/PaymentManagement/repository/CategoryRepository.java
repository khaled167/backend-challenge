package com.passboard.PaymentManagement.repository;

import com.passboard.PaymentManagement.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByType(String type);

    Optional<Category> findByType(String type);
}