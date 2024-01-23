package com.passboard.DigitalIdentityManagement.repository;


import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>, PagingAndSortingRepository<Visitor, Long> {
    Optional<Visitor> findByUsernameAndActiveIsTrue(String username);

    Visitor findByIdAndActiveIsTrue(Long id);
    List<Visitor> findAllByActive(Boolean active);
}
