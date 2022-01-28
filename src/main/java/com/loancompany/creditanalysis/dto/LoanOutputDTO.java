package com.loancompany.creditanalysis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanOutputDTO {
	
	private Long id;
	
	private BigDecimal value;
	
	private LocalDate dateFirstInstallment;
	
	private Integer installmentQuantity;

}
