package com.loancompany.creditanalysis.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientInputDTO {
	
	@NotBlank(message = "The name field is mandatory")
	private String name;
	
	@CPF(message = "Invalid CPF")
	private String cpf;
	
	@NotBlank(message = "The rg field is mandatory")
	private String rg;
	
	@NotNull(message = "The income field is mandatory")
	private BigDecimal income;
	
	@Email(message = "Invalid e-mail")
	private String email;
	
	@NotBlank(message = "The password field is mandatory")
	@Size(min = 3, message = "The password field must be 3 or more characters")
	private String password;
	
	@Valid
	private List<AddressInputDTO> addresses;

}
