package org.restaurant.services;

import java.time.LocalDate;
import java.util.List;

import org.restaurant.dto.Revenu;
import org.restaurant.dto.TicketDto;
import org.restaurant.entity.Ticket;
import org.springframework.http.ResponseEntity;

public interface TicketService {
	
	public Ticket addTicket(TicketDto ticket);
	public Ticket updateTicket(Ticket ticket);
	public ResponseEntity<String> deleteTicket(int id);
	Ticket getTicket(int numero);
	List<Ticket> listTicket(LocalDate date);
	public Revenu RevenuByDayMonthYear(LocalDate d1);
	public double revenubyPeriod(LocalDate d1, LocalDate d2);
}
