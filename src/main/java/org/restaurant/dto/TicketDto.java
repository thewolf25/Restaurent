package org.restaurant.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.restaurant.entity.Client;
import org.restaurant.entity.Met;
import org.restaurant.entity.Table;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TicketDto {
	
	private int numero;
	@PastOrPresent(message = "Date non valide")
	private LocalDateTime date;
	@Min(2)
	private int nbCouvert;
	private double addition;
	private Client client;
	@NotNull(message = "Vous devez selectinner une table")
	private Table table;
	@NotNull(message = "Vous devez tapez au moins un Met")
	private List<Metdto> mets;
		
}
