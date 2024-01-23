package com.passboard.DigitalIdentityManagement.service;


import com.passboard.DigitalIdentityManagement.mapper.VisitorMapper;
import com.passboard.DigitalIdentityManagement.model.dto.VisitorDto;
import com.passboard.DigitalIdentityManagement.model.entity.Visitor;
import com.passboard.DigitalIdentityManagement.repository.VisitorRepository;
import com.passboard.PaymentManagement.model.entity.Event;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    public VisitorService(VisitorRepository visitorRepository, VisitorMapper visitorMapper) {
        this.visitorRepository = visitorRepository;
        this.visitorMapper = visitorMapper;
    }

    public List<Visitor> getVisitors() {
        return visitorRepository.findAllByActive(true);
    }

    public Visitor getVisitor(Long id) {
        return visitorRepository.findByIdAndActiveIsTrue(id);
    }

    public Visitor addVisitor(@Valid VisitorDto visitorDto) {
        return visitorRepository.save(visitorMapper.toEntity(visitorDto));
    }

    public void updateVisitor(Long id, @Valid VisitorDto visitorDto) {
        visitorRepository.findById(id).ifPresent(visitor -> visitorRepository.save(visitorMapper.partialUpdate(visitorDto, visitor)));
    }

    public void deleteVisitor(Long id) {
        visitorRepository.findById(id).ifPresent(visitor -> {
            visitor.setActive(false);
            visitorRepository.save(visitor);
        });
    }


    /**
     * recommendation algorithm based on user interests and history, a relevant events only returns to him.
     * to be implemented by advanced alternatives such as Slope One, Collaborative filtering and Content-based filtering algorithms
     */
    public ResponseEntity<List<Event>> getRelevantEvents(Long visitorId) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
