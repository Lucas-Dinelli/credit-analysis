package com.loancompany.creditanalysis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loancompany.creditanalysis.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	@Query("SELECT l FROM Loan l WHERE l.client.id = :clientId")
	List<Loan> findAllByClientId(@Param(value = "clientId") Long clientId);
	
	@Query("SELECT l FROM Loan l WHERE l.id = :id AND l.client.email = :clientEmail")
	Optional<Loan> findByIdAndClientEmail(
			@Param(value = "id") Long id, 
			@Param(value = "clientEmail") String clientEmail
	);
	
}
