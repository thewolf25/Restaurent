package org.restaurant.controllers;

import org.restaurant.dto.TableDto;
import org.restaurant.services.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
@RequestMapping("/table")
@AllArgsConstructor
@RestController
public class TableController {
	TableService tableService;
	
	@PostMapping("/add")
	public TableDto addTable(@RequestBody TableDto table) {
		return tableService.addTable(table);
	}
	
	
	@GetMapping("/mostReserved")
	public TableDto getTableMostReserved() {
		return tableService.getTableMostReserved();
	}
}
