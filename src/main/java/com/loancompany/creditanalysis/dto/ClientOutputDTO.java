package com.loancompany.creditanalysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientOutputDTO {
	
	@JsonIgnore
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;	// WITHDRAW

}
