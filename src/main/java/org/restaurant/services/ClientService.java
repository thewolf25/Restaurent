package org.restaurant.services;

import java.util.List;

import org.restaurant.dto.ClientDto;
import org.springframework.http.ResponseEntity;

public interface ClientService {
	
	ClientDto addClient(ClientDto clientDto);
	ClientDto updateClient(ClientDto clientDto);
	ResponseEntity<String> deleteClient(ClientDto clientDto);
	ClientDto getClientByid(Long id);
	List<ClientDto> listClients();
	ClientDto plusFidele();
	String jourPlusReserver(ClientDto client);
}
