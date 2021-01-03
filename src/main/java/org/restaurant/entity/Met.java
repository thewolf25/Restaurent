package org.restaurant.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorColumn(name = "type")
@JsonSubTypes(
		{ 
		@Type(name = "Dessert", value = Dessert.class ),
		@Type(name = "Entree", value = Entree.class),
		@Type(name = "Plat", value = Plat.class)
		})
public abstract class Met {
	@Id
	@Column(length = 18)
	private String nom;
	private double prix;
	
	@ManyToMany(mappedBy = "mets")
	
	private List<Ticket> tickets;
}
