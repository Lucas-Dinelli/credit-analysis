package com.loancompany.creditanalysis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loancompany.creditanalysis.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByEmail(String email);
	
}
