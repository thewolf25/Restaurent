package org.restaurant.controllers;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.restaurant.dto.ClientDto;
import org.restaurant.exceptions.ClientException;
import org.restaurant.response.ResponseClient;
import org.restaurant.security.models.AuthenticationRequest;
import org.restaurant.security.models.AuthenticationResponse;
import org.restaurant.security.utils.JwtUtil;
import org.restaurant.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController

@AllArgsConstructor
//@CrossOrigin("*")
public class ClientController {
	private ClientService clientService;
	private UserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate") 
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrecet username or password");
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity(new AuthenticationResponse(jwt),HttpStatus.OK);
	}
	
	
	@PostMapping("/client/add")
	public ResponseEntity<ResponseClient<ClientDto>> addClient(@Valid @RequestBody ClientDto client,BindingResult bindResult) {
		if(bindResult.hasErrors()) {
			bindResult.getAllErrors();
			return new ResponseEntity<ResponseClient<ClientDto>> (new ResponseClient<ClientDto>(HttpStatus.BAD_REQUEST,null,bindResult.getAllErrors().stream().map(c -> c.getDefaultMessage()).collect(Collectors.toList()),"priere de corriger les erruers"),HttpStatus.BAD_REQUEST);
		}
		
		
		ResponseClient<ClientDto> response= new ResponseClient<ClientDto>();
		response.setData(clientService.addClient(client));
		response.setStatus(HttpStatus.OK);
		response.setMessage("ajoutée avec succée");
		
		
		return new ResponseEntity<ResponseClient<ClientDto>> (response,HttpStatus.OK);
				
	}
	
	
	@PutMapping("/client/update")
	public ClientDto updateClient(@Valid @RequestBody ClientDto client ) {
		return clientService.updateClient(client);
	}
	
	
	@DeleteMapping("/client/delete")
	public ResponseEntity<String> deleteClient( @RequestBody ClientDto client ) {
		return clientService.deleteClient(client);
	}
	
	
	@GetMapping("/client/list")
	public List<ClientDto> listClients(){
		return clientService.listClients();
	}
	@PostMapping("/client/jourplusreserver")
	String jourPlusReserver(@RequestBody ClientDto client) {
		return clientService.jourPlusReserver(client);
	}
	
	
	@GetMapping("/client/get/{id}")
	public ClientDto getClientById(@PathVariable Long id) {
		return clientService.getClientByid(id);
	}
	
	
	@GetMapping("/client/plusfidele")
	public ClientDto plusFidele() {
		return clientService.plusFidele();
	}
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<String> handleClientException(ClientException c){
		
		return new ResponseEntity<String>(c.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
}
