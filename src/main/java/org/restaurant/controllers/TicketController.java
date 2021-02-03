package org.restaurant.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.restaurant.dto.ClientDto;
import org.restaurant.dto.Metdto;
import org.restaurant.dto.Revenu;
import org.restaurant.dto.TicketDto;
import org.restaurant.entity.Met;
import org.restaurant.entity.Ticket;
import org.restaurant.response.ResponseClient;
import org.restaurant.services.MetService;
import org.restaurant.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
	private TicketService ticketService;
	private MetService metService;
	@PostMapping("/add")
	public Ticket addTicket(@RequestBody TicketDto ticket) {
		return ticketService.addTicket(ticket);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") int id){
		return ticketService.deleteTicket(id);
	}
	
	
	
	@GetMapping("/list/date")
	public List<Ticket> listTicketByDate(@RequestParam(name = "date") String date){
		return ticketService.listTicket(LocalDate.parse(date));
	}
	

	@GetMapping("/getRevenuePourPeriode/{date1}/{date2}")
	public double getRevenue(@PathVariable("date1") String d1,@PathVariable("date2") String d2) {
		
		LocalDate date1,date2;
		
		try {
			date1 = LocalDate.parse(d1);
			date2 = LocalDate.parse(d2);
		}
		catch(Exception e){
			throw new RuntimeException("date invalide");
		}
		
		return ticketService.revenubyPeriod(date1, date2);
	}
	@GetMapping("/getRevenueByDayWeekMonth/{d1}")
	public Revenu getRevenuePourPeriode(@PathVariable("d1") String d1) {
LocalDate date1;
		
		try {
			date1 = LocalDate.parse(d1);
		
		}
		catch(Exception e){
			throw new RuntimeException("date invalide");
		}
		
		return ticketService.RevenuByDayMonthYear(date1);
	}
	

	
	
}
