package org.restaurant.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Revenu {
	private LocalDate date;
	private double parJour;
	private double parSemaine;
	private double parMois;

public String getdate() {
	
	return date.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy")) + "   week n°=" + date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
} 


}

