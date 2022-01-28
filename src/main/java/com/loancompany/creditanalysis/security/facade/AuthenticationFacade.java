package com.loancompany.creditanalysis.security.facade;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
	
	Authentication getAuthentication();

}
