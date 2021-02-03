package org.restaurant.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.restaurant.dto.Metdto;
import org.restaurant.services.MetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MetController {
	private MetService metService;
	
	@PostMapping("/met/add")
	public ResponseEntity<String> addMet(@Valid @RequestBody Metdto met,BindingResult bindResult) {
		
		
		if(bindResult.hasErrors()) {
			
			return new ResponseEntity<String> (bindResult.getFieldErrors().get(0).getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		metService.ajouterMet(met);
		return new ResponseEntity<String> ("sucess",HttpStatus.OK);
	}
	
	@DeleteMapping("/met/delete")
	public ResponseEntity<String> deleteMet(@Valid @RequestBody Metdto met){
		return metService.deleteMet(met);
	}
	
	
	@GetMapping("/met/list")
	public List<Metdto> listMet(){
		return metService.listMet();
	}
	
	
	@GetMapping("/met/get/{name}")
	public Metdto getMetById(@PathVariable("name") String nom ) {
		return metService.getMetById(nom);
	}
	
	
	
	@GetMapping("/met/prefere/get")
	public Metdto getPlatPlusReserve(@RequestParam("d1")String d1, @RequestParam("d2") String d2) {
		LocalDate date1,date2;
		
		try {
			date1 = LocalDate.parse(d1);
			date2 = LocalDate.parse(d2);
		}
		catch(Exception e){
			throw new RuntimeException("date invalide");
		}
	
		return metService.getPlatPlusReserve(LocalDate.parse(d1),LocalDate.parse(d2));
	}
	
	
	
	@ExceptionHandler
	ResponseEntity<String> handleException( RuntimeException e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
