package com.lambda.bilan.web.boot;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lambda.bilan.entities.Utilisateur;

public class AppUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	// propriétés
	private Utilisateur user;

	// constructeurs
	public AppUserDetails() {
	}

	public AppUserDetails(Utilisateur user) {
		this.user = user;
	}

	// -------------------------interface
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String role=getRole(user);
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPasswordUtilisateur();
	}

	@Override
	public String getUsername() {
		return user.getEmailUtilisateur();
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

	// getters et setters
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
		//les roles dans spring security doivent etre définis sous forme "ROLE_NOMROLE"
		//et interogé sous forme "NOMROLE" 
		public static String getRole(Utilisateur user){
			return "ROLE_"+user.getClass().getName().split("\\.")[4].toUpperCase();	
		}

}
