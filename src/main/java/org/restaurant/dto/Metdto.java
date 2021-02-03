package org.restaurant.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.Check;

import lombok.Data;
@Data
public class Metdto {
	@NotBlank
	private String nom;
	@PositiveOrZero
	private double prix;
	@Pattern(regexp = "^(Plat|Entree|Dessert){1}$",message = "vous devez choisir type de plat valide")
	private String type;
}
