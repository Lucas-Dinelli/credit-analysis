package com.loancompany.creditanalysis.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loancompany.creditanalysis.dto.LoanInputDTO;
import com.loancompany.creditanalysis.dto.LoanOutputDTO;
import com.loancompany.creditanalysis.dto.LoanSpecificOutputDTO;
import com.loancompany.creditanalysis.model.Loan;
import com.loancompany.creditanalysis.repository.LoanRepository;
import com.loancompany.creditanalysis.security.facade.AuthenticationFacade;
import com.loancompany.creditanalysis.service.exceptions.DateFirstInstallmentException;
import com.loancompany.creditanalysis.service.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoanService {
	
	private LoanRepository loanRepository;
	
	private ModelMapper modelMapper;
	
	private ClientService clientService;
	
	private AuthenticationFacade authenticationFacade;
	
	public LoanOutputDTO create(LoanInputDTO loanInputDTO) {
		checkDateFirstInstallment(loanInputDTO.getDateFirstInstallment());
		Loan loan = modelMapper.map(loanInputDTO, Loan.class);
		loan.setClient(clientService.findByEmail(getCurrentEmailToLoan()));
		return modelMapper.map(loanRepository.save(loan), LoanOutputDTO.class);
	}
	
	public List<LoanOutputDTO> listAll() {
		Long clientId = clientService.findByEmail(getCurrentEmailToLoan()).getId();
		List<Loan> loans = loanRepository.findAllByClientId(clientId);
		return loans.stream()
				.map(loan -> modelMapper
						.map(loan, LoanOutputDTO.class))
				.collect(Collectors.toList());
	}
	
	public LoanSpecificOutputDTO findById(Long id) {
		Loan loan = loanRepository
				.findByIdAndClientEmail(id, getCurrentEmailToLoan())
				.orElseThrow(() -> new ObjectNotFoundException("Loan not found!"));
		LoanSpecificOutputDTO loanSpecificOutputDTO = modelMapper.map(loan, LoanSpecificOutputDTO.class);
		loanSpecificOutputDTO.setClientEmail(loan.getClient().getEmail());
		loanSpecificOutputDTO.setClientIncome(loan.getClient().getIncome());
		return loanSpecificOutputDTO;
	}
	
	private void checkDateFirstInstallment(LocalDate dateFirstInstallment) {
		LocalDate maximumDate = LocalDate.now().plusMonths(3);
		LocalDate minimumDate = LocalDate.now().plusMonths(1);
		
		if(dateFirstInstallment.isBefore(minimumDate) || dateFirstInstallment.isAfter(maximumDate))
			throw new DateFirstInstallmentException("The date first installment must be between "
					+ minimumDate + " and " + maximumDate);
	}
	
	private String getCurrentEmailToLoan() {
		return authenticationFacade.getAuthentication().getName();
	}

}
