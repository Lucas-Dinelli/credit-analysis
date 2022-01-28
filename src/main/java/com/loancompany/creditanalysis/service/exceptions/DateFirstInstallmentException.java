package com.loancompany.creditanalysis.service.exceptions;

public class DateFirstInstallmentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DateFirstInstallmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateFirstInstallmentException(String message) {
		super(message);
	}

}
