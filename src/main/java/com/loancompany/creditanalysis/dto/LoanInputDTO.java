package com.loancompany.creditanalysis.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanInputDTO {
	
	@NotNull(message = "The value field is mandatory")
	private BigDecimal value;
	
	@NotNull(message = "The dateFirstInstallment is mandatory")
	private LocalDate dateFirstInstallment;
	
	@NotNull(message = "The installmentQuantity is mandatory")
	@Max(value = 60, message = "The maximum installment quantity is 60")
	@Min(value = 1, message = "The minimum installment quantity is 1")
	private Integer installmentQuantity;
	
}
