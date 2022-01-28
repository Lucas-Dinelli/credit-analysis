package com.loancompany.creditanalysis.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanSpecificOutputDTO extends LoanOutputDTO {
	
	private String clientEmail;
	
	private BigDecimal clientIncome;

}
