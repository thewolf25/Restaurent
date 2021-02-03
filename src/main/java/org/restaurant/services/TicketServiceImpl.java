package org.restaurant.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.restaurant.dto.Revenu;
import org.restaurant.dto.TicketDto;
import org.restaurant.entity.Client;
import org.restaurant.entity.Met;
import org.restaurant.entity.Ticket;
import org.restaurant.exceptions.TableException;
import org.restaurant.repository.ClientRepository;
import org.restaurant.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Service
@AllArgsConstructor
@Getter@Setter
public class TicketServiceImpl implements TicketService{

	private ClientRepository clientRepository;
	private TableService tableService;
	private ModelMapper modelMapper;
	private TicketRepository ticketRepository;
	private MetService MetService;
	@Override
	public Ticket addTicket(TicketDto ticket) {
		// TODO Auto-generated method stub
		Ticket t ;
		Client c = clientRepository.findById(ticket.getClient().getId()).get();
		if(c == null) {
			c = clientRepository.save(ticket.getClient());
		}
		ticket.setClient(c);
		tableService.getTable(ticket.getTable().getNumero());
		List<Met> lis = ticket.getMets()
				.stream()
				.map((s) -> MetService.metMapper(s)).collect(Collectors.toList());
		ticket.setMets(null);
		t = modelMapper.map(ticket, Ticket.class);
		t.setMets(lis);
		
		return ticketRepository.save(t);
	}

	@Override
	public Ticket getTicket(int numero) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(numero).orElseThrow(()-> new TableException("ticket not found"));
	}

	@Override
	public List<Ticket> listTicket(LocalDate date) {
		List<Ticket> ticketdb= ticketRepository.findAll().stream().filter(s -> s.getDate().toLocalDate().equals(date)).collect(Collectors.toList());
		return ticketdb;
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteTicket(int id) {
		// TODO Auto-generated method stub
		ticketRepository.delete(getTicket(id));
		return new ResponseEntity<String> ("Le ticket n°="+id+ "est supprimé avec succées",HttpStatus.OK);
	}
	
	
	public double revenubyPeriod(LocalDate d1, LocalDate d2) {
		List<Ticket> ticketdb = ticketRepository.getTicketbyPeriod(LocalDateTime.of(d1, LocalTime.of(0, 0, 0)),LocalDateTime.of(d2, LocalTime.of(0, 0, 0)));
	
		return ticketdb.stream().mapToDouble(t -> t.getAddition()).sum();	
	}
	
	
	public Revenu RevenuByDayMonthYear(LocalDate d1) {
		List<Ticket> ticketdb = ticketRepository.findAll();
		Revenu R1 = new Revenu();
		R1.setDate(d1);
		R1.setParSemaine(ticketdb.stream().filter(t -> t.getDate().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()) == d1.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())).mapToDouble(t -> t.getAddition()).sum());
		R1.setParMois(ticketdb.stream().filter(t -> t.getDate().getMonth().toString().equals(d1.getMonth().toString())).mapToDouble(t -> t.getAddition()).sum());
		R1.setParJour(ticketdb.stream().filter(t -> t.getDate().toLocalDate().equals(d1)).mapToDouble(t -> t.getAddition()).sum());
		return R1;	
	}

}
