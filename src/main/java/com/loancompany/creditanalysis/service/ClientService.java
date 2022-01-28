package com.loancompany.creditanalysis.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loancompany.creditanalysis.dto.ClientInputDTO;
import com.loancompany.creditanalysis.dto.ClientOutputDTO;
import com.loancompany.creditanalysis.model.Client;
import com.loancompany.creditanalysis.repository.ClientRepository;
import com.loancompany.creditanalysis.service.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {
	
	private ClientRepository clientRepository;
	
	private ModelMapper modelMapper;
	
	private PasswordEncoder passwordEncoder;
	
	private AddressService addressService;
	
	public ClientOutputDTO create(ClientInputDTO clientInputDTO) {
		Client client = modelMapper.map(clientInputDTO, Client.class);
		client.setPassword(this.encode(client.getPassword()));
		verifyExistingAddresses(client);
		return modelMapper.map(clientRepository.save(client), ClientOutputDTO.class);
	}
	
	public Client findByEmail(String email) {
		return clientRepository.findByEmail(email).orElseThrow(
				() -> new ObjectNotFoundException("Client not found!")
		);
	}
	
	private String encode(String textToEncode) {
		return passwordEncoder.encode(textToEncode);
	}
	
	private void verifyExistingAddresses(Client client) {
		client.getAddresses()
			.forEach(address -> addressService
					.save(address));
	}
	
}
