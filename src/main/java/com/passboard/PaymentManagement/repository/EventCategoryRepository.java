package com.passboard.PaymentManagement.repository;

import com.passboard.PaymentManagement.model.entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    List<EventCategory> findAllByEventId(Long eventId);
}