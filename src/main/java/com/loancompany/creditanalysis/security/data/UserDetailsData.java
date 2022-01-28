package com.loancompany.creditanalysis.security.data;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.loancompany.creditanalysis.model.Client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsData implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Client client;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.client.getPassword();
	}

	@Override
	public String getUsername() {
		return this.client.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
