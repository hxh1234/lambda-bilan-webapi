package com.lambda.bilan.web.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lambda.bilan.dao.UtilisateurDAO2;
import com.lambda.bilan.entities.Utilisateur;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurDAO2 dao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// on cherche l'utilisateur via son login
		Utilisateur user = dao.findUserByEmailUtilisateur(email);	
		// trouvé ?
		if (user == null) {
			throw new UsernameNotFoundException(String.format("login [%s] inexistant", email));
		}
		// on rend les détails de l'utilsateur
		return new AppUserDetails(user);
	}

}
