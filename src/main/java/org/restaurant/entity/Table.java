package org.restaurant.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
public class Table {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private int nbCouvert;
	private String type;
	private double supplement;
	@OneToMany(mappedBy = "table")
	@JsonIgnore
	private List<Ticket> ticket;
	
}

