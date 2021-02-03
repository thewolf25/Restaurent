package org.restaurant.services;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.restaurant.dto.Metdto;
import org.restaurant.entity.Met;
import org.springframework.http.ResponseEntity;

public interface MetService {

	Met ajouterMet(Metdto met);
	ResponseEntity<String> deleteMet(Metdto met);
	List<Metdto> listMet();
	Metdto getMetById(String metName);
	Metdto getPlatPlusReserve(LocalDate d1, LocalDate d2);
	Met metMapper(Metdto met);
}
