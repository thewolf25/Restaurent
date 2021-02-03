package org.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableDto {
	private int numero;
	private int nbCouvert;
	private String type;
	private double supplement;
	
}
