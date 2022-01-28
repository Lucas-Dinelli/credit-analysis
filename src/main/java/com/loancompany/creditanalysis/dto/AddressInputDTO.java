package com.loancompany.creditanalysis.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.loancompany.creditanalysis.model.AddressType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInputDTO {
	
	@NotBlank(message = "The state field is mandatory")
	private String state;
	
	@NotBlank(message = "The city field is mandatory")
	private String city;
	
	@NotBlank(message = "The district field is mandatory")
	private String district;
	
	@NotBlank(message = "The publicArea field is mandatory")
	private String publicArea;
	
	@NotNull(message = "The number field is mandatory")
	private Integer number;
	
	@Getter(value = AccessLevel.NONE)
	private String complement;
	
	@NotNull(message = "The addressType field must be entered with the following values: [HOME or COMMERCIAL]")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	public String getComplement() {
		if(this.complement == null)
			return "";
		
		return this.complement;
	}

}
