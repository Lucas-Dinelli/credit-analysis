package com.loancompany.creditanalysis.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityVariables {
	
	public static Integer TOKEN_EXPIRATION;
	public static String TOKEN_PASSWORD;
	public static String ATTRIBUTE_HEADER;
	public static String ATTRIBUTE_PREFIX;
	
	public SecurityVariables(
							@Value("${token.expiration}") Integer tokenExpiration,
							@Value("${token.password}") String tokenPassword, 
							@Value("${attribute.header}") String attributeHeader,
							@Value("${attribute.prefix}") String attributePrefix) {

		SecurityVariables.TOKEN_EXPIRATION = tokenExpiration;
		SecurityVariables.TOKEN_PASSWORD = tokenPassword;

		SecurityVariables.ATTRIBUTE_HEADER = attributeHeader;
		SecurityVariables.ATTRIBUTE_PREFIX = attributePrefix;
	}

}
