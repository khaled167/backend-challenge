package com.passboard.DigitalIdentityManagement.controller;

import com.passboard.DigitalIdentityManagement.model.dto.VisitorDto;
import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import com.passboard.DigitalIdentityManagement.service.VisitorService;
import com.passboard.PaymentManagement.model.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors/v1")
@Slf4j
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getVisitors() {
        return ResponseEntity.ok(visitorService.getVisitors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable Long id) {
        return ResponseEntity.ok(visitorService.getVisitor(id));
    }

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody VisitorDto visitorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitorService.addVisitor(visitorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVisitor(@PathVariable Long id, @RequestBody VisitorDto visitorDto) {
        visitorService.updateVisitor(id, visitorDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<Event>> getRelevantEvents(@PathVariable Long id) {
        return visitorService.getRelevantEvents(id);
    }
}
