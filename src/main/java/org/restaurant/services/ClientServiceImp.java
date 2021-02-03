package org.restaurant.services;

import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgCollectionCacheType;
import org.modelmapper.ModelMapper;
import org.restaurant.dto.ClientDto;
import org.restaurant.entity.Client;
import org.restaurant.entity.Ticket;
import org.restaurant.exceptions.ClientException;
import org.restaurant.repository.ClientRepository;
import org.restaurant.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImp implements ClientService{
	private ClientRepository clientRepository;
	private ModelMapper modelMapper;
	private TicketRepository ticketRepository;
	
	@Override
	public ClientDto addClient(ClientDto clientDto) {
		// TODO Auto-generated method stub
		Client client = modelMapper.map(clientDto, Client.class);
		client = clientRepository.save(client);
		return modelMapper.map(client, ClientDto.class);
	}

	@Override
	public ClientDto updateClient(ClientDto clientDto) {
		// TODO Auto-generated method stub
		Client clientdb = chercherClient(clientDto);
		List<Ticket> ticket= clientdb.getTickets();
		clientdb = modelMapper.map(clientDto, Client.class); 
		clientdb.setTickets(ticket);
		clientRepository.save(clientdb);
		return clientDto;
	}

	@Override
	public ResponseEntity<String> deleteClient(ClientDto clientDto) {
		Client clientdb = chercherClient(clientDto);
		clientRepository.delete(clientdb);
		return new ResponseEntity<String>("Supprimer avec succes",HttpStatus.OK);
	}
	
	
	public Client chercherClient(ClientDto clientDto) {
		return clientRepository.findById(clientDto.getId()).orElseThrow(()->new ClientException("Client non inscrit dans la base !!"));
	}

	@Override
	public ClientDto getClientByid(Long id) {
		
		return modelMapper.map(clientRepository.findById(id).orElseThrow(()->new ClientException("Client non inscrit dans la base !!")), ClientDto.class);
	}

	@Override
	public List<ClientDto> listClients() {
		return clientRepository.findAll().stream().map(c-> modelMapper.map(c, ClientDto.class)).collect(Collectors.toList());
	}

	@Override
	public ClientDto plusFidele() {
		// TODO Auto-generated method stub
		List<Client> client = clientRepository.findAll().stream().sorted((c1,c2) -> ((Integer)c1.getTickets().size()).compareTo((Integer)c2.getTickets().size())).collect(Collectors.toList());

		return modelMapper.map(client.get(client.size() -1), ClientDto.class);
	}

	@Override
	public String jourPlusReserver(ClientDto client) {
		// TODO Auto-generated method stub
		String plusReserver = null;
		int max = 0;
		int m = 0;
		Client c = clientRepository.findById(client.getId()).orElseThrow(()->new ClientException("client non enregistré dans la base"));
		List<String> jours = c.getTickets().stream().map(f->f.getDate().getDayOfWeek().toString()).collect(Collectors.toList());
		for (String jour : jours.stream().distinct().collect(Collectors.toList())){			 
			m = Collections.frequency(jours, jour);			
			if(m>max) {
				max = m;
				plusReserver = jour;
			}
		}
		return plusReserver;
	}

	

}
