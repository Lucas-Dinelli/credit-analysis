package com.loancompany.creditanalysis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loancompany.creditanalysis.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	@Query("SELECT a.id FROM Address a WHERE "
			+ "a.state = :state "
					+ "AND "
			+ "a.city = :city "
					+ "AND "
			+ "a.district = :district "
					+ "AND "
			+ "a.publicArea = :publicArea "
					+ "AND "
			+ "a.number = :number "
					+ "AND "
			+ "a.complement = :complement ")
	Optional<Long> findAddressesByAttributtes(
			@Param(value = "state") String state, 
			@Param(value = "city") String city, 
			@Param(value = "district") String district,
			@Param(value = "publicArea") String publicArea,
			@Param(value = "number") Integer number,
			@Param(value = "complement") String complement);
	
}
