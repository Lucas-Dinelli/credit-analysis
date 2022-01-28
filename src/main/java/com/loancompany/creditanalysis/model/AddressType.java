package com.loancompany.creditanalysis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AddressType {
	
	HOME("Home"),
	COMMERCIAL("Commercial");
	
	private final String descriptionAddress;

}
