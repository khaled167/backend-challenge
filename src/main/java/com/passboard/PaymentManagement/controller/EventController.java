package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.mapper.EventMapper;
import com.passboard.PaymentManagement.model.dto.EventDto;
import com.passboard.PaymentManagement.model.entity.Category;
import com.passboard.PaymentManagement.model.entity.Event;
import com.passboard.PaymentManagement.model.entity.EventCategory;
import com.passboard.PaymentManagement.repository.EventCategoryRepository;
import com.passboard.PaymentManagement.repository.EventRepository;
import com.passboard.PaymentManagement.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/events/v1")
public class EventController {

    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final EventMapper eventMapper;
    private final CategoryService categoryService;

    public EventController(EventRepository eventRepository, EventCategoryRepository eventCategoryRepository, EventMapper eventMapper, CategoryService categoryService) {
        this.eventRepository = eventRepository;
        this.eventCategoryRepository = eventCategoryRepository;
        this.eventMapper = eventMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody EventDto eventDto) {
        List<Category> categories = categoryService.findOrPersist(eventDto.getCategories());
        Event event = eventRepository.save(eventMapper.toEntity(eventDto));
        IntStream.rangeClosed(1, categories.size())
                .mapToObj(order -> new EventCategory((short) order, event, categories.get(order-1)))
                .forEachOrdered(eventCategoryRepository::save);
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long eventId) {
        EventDto eventDto = eventMapper.toDto(eventRepository.findById(eventId).orElseThrow()).setCategories(new ArrayList<>());
        eventCategoryRepository.findAllByEventId(eventId).forEach(eventCategory -> eventDto.getCategories().add(eventCategory.getCategory().getType()));
        return ResponseEntity.ok(eventDto);
    }
}
