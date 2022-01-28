package com.loancompany.creditanalysis.security;

import static com.loancompany.creditanalysis.security.SecurityVariables.ATTRIBUTE_PREFIX;
import static com.loancompany.creditanalysis.security.SecurityVariables.TOKEN_EXPIRATION;
import static com.loancompany.creditanalysis.security.SecurityVariables.TOKEN_PASSWORD;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loancompany.creditanalysis.model.Client;
import com.loancompany.creditanalysis.security.data.UserDetailsData;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
												HttpServletResponse response) throws AuthenticationException {
		try {
			Client client = new ObjectMapper().readValue(request.getInputStream(), Client.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					client.getEmail(), 
					client.getPassword())
			);
			
		} catch (IOException e) {
			throw new RuntimeException("Client authentication failed", e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		
		UserDetailsData userDetailsData = (UserDetailsData) authResult.getPrincipal();
		String token = createToken(userDetailsData);

		response.getWriter().write(ATTRIBUTE_PREFIX + token);
		response.getWriter().flush();
	}
	
	private String createToken(UserDetailsData userDetailsData) {
		return JWT.create()
				.withSubject(userDetailsData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
	}

}
