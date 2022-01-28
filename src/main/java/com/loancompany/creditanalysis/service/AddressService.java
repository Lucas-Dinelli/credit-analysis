package com.loancompany.creditanalysis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loancompany.creditanalysis.model.Address;
import com.loancompany.creditanalysis.repository.AddressRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {
	
	private AddressRepository addressRepository;
	
	public void save(Address address) {
		Optional<Long> id = findAddressesByAttributtes(address);
		
		if(id.isPresent())
			update(id.get(), address);
		
		addressRepository.save(address);
	}
	
	private void update(Long id, Address address) {
		address.setId(id);
		addressRepository.save(address);
	}
	
	private Optional<Long> findAddressesByAttributtes(Address address) {
		return addressRepository.findAddressesByAttributtes(
				address.getState(),
				address.getCity(),
				address.getDistrict(),
				address.getPublicArea(),
				address.getNumber(),
				address.getComplement()
		);
	}
}
