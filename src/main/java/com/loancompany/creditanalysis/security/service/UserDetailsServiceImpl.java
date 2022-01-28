package com.loancompany.creditanalysis.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.loancompany.creditanalysis.model.Client;
import com.loancompany.creditanalysis.repository.ClientRepository;
import com.loancompany.creditanalysis.security.data.UserDetailsData;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Client> client = clientRepository.findByEmail(email);
		return client
				.map(UserDetailsData::new)
				.orElseThrow(() -> new UsernameNotFoundException("Email [" + email + "] not found!"));
	}

	
	
	

}
