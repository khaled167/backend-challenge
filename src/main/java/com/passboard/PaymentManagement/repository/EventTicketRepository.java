package com.passboard.PaymentManagement.repository;

import com.passboard.PaymentManagement.model.entity.EventTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTicketRepository extends JpaRepository<EventTicket, Long> {
    EventTicket findEventTicketByEvent_IdAndTicket_Id(Long eventId, Long ticketId);
}