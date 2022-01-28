package com.loancompany.creditanalysis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.loancompany.creditanalysis.security.service.UserDetailsServiceImpl;

import static com.loancompany.creditanalysis.security.SecurityVariables.ATTRIBUTE_HEADER;
import static com.loancompany.creditanalysis.security.SecurityVariables.ATTRIBUTE_PREFIX;
import static com.loancompany.creditanalysis.security.SecurityVariables.TOKEN_PASSWORD;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

public class JWTValidationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public JWTValidationFilter(AuthenticationManager authenticationManager, 
								UserDetailsServiceImpl userDetailsServiceImpl) {
		super(authenticationManager);
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain) throws IOException, ServletException {
		
		String attribute = request.getHeader(ATTRIBUTE_HEADER);
		
		if(attribute == null || !attribute.startsWith(ATTRIBUTE_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = attribute.replace(ATTRIBUTE_PREFIX, "");
		
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		String username = JWT.require(Algorithm.HMAC512(TOKEN_PASSWORD))
				.build()
				.verify(token)
				.getSubject();
		
		if(username == null)
			return null;
		
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
	}

}
