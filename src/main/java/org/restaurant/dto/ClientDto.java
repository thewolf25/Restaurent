package org.restaurant.dto;



import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
	
	
	private Long id = (long) 0;
	@NotNull(message = "le Nom est obligatoire")
	@NotBlank(message = "le Nom est obligatoire")
	private String nom;
	@NotBlank(message = "le Prenom est obligatoire")
	private String prenom;
	@Past(message = "Date non valide")
	private LocalDate dateDeNaissance;
	@Email(message = "email non valide")
	private String courriel;
	@Pattern(regexp = "^\\d{8}$")
	private String telephone;
}
