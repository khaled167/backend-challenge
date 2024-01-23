package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.mapper.EventTicketMapper;
import com.passboard.PaymentManagement.model.dto.EventTicketDto;
import com.passboard.PaymentManagement.model.entity.EventTicket;
import com.passboard.PaymentManagement.repository.EventRepository;
import com.passboard.PaymentManagement.repository.EventTicketRepository;
import com.passboard.PaymentManagement.repository.TicketRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eventTickets")
public class EventTicketController {

    private final EventTicketRepository eventTicketRepository;
    private final EventTicketMapper eventTicketMapper;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;

    public EventTicketController(EventTicketRepository eventTicketRepository, EventTicketMapper eventTicketMapper, EventRepository eventRepository, TicketRepository ticketRepository) {
        this.eventTicketRepository = eventTicketRepository;
        this.eventTicketMapper = eventTicketMapper;
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostMapping
    public EventTicket addEventTicket(@RequestBody EventTicketDto eventTicketDto) {
        System.out.println(eventTicketDto);
        System.out.println(eventTicketMapper.toEntity(eventTicketDto));
        EventTicket eventTicket = eventTicketMapper.toEntity(eventTicketDto);
        eventTicket.setEvent(eventRepository.findById(eventTicketDto.getEventId()).orElseThrow());
        eventTicket.setTicket(ticketRepository.findById(eventTicketDto.getTicketId()).orElseThrow());
        return eventTicketRepository.save(eventTicket);
    }
}
