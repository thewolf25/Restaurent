package org.restaurant.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int numero;
private LocalDateTime date;
private int nbCouvert;
private double addition;


@ManyToOne
private Client client;

@ManyToOne
private Table table;
@ManyToMany
@JoinTable(name = "Ticket_Mets",joinColumns = {
		@JoinColumn (name = "Ticket_id")},
inverseJoinColumns = {
		@JoinColumn(name = "Met_id")}
)
private List<Met> mets;
	
}
