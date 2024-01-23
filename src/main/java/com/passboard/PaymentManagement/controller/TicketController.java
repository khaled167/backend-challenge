package com.passboard.PaymentManagement.controller;


import com.passboard.PaymentManagement.mapper.TicketMapper;
import com.passboard.PaymentManagement.model.dto.TicketDto;
import com.passboard.PaymentManagement.model.entity.Ticket;
import com.passboard.PaymentManagement.repository.TicketRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets/v1")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    public TicketController(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @PostMapping
    public Ticket addTicket(@RequestBody TicketDto ticketDto) {
        System.out.println(ticketDto);
        System.out.println(ticketMapper.toEntity(ticketDto));
        return ticketRepository.save(ticketMapper.toEntity(ticketDto));
    }
}
