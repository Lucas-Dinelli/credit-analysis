package com.loancompany.creditanalysis.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loancompany.creditanalysis.dto.ClientInputDTO;
import com.loancompany.creditanalysis.dto.ClientOutputDTO;
import com.loancompany.creditanalysis.service.ClientService;

@RestController
@RequestMapping("/v1/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<ClientOutputDTO> create(@Valid @RequestBody ClientInputDTO clientInputDTO) {
		ClientOutputDTO clientOutputDTO = clientService.create(clientInputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(clientOutputDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(clientOutputDTO);
	}
	
	@GetMapping
	public ResponseEntity<String> hello(@AuthenticationPrincipal UserDetails userDetails) {
		String credentials = "Email = " + userDetails.getUsername() 
							+ " / Password = " + userDetails.getPassword();
		return ResponseEntity.ok().body(credentials);
	}
	
}
