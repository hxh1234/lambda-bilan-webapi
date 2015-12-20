package com.lambda.bilan.web.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableAutoConfiguration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		// l'authentification est faite par le bean [appUserDetailsService]
		// le mot de passe est crypté par l'algorithme de hachage BCrypt
		registry.userDetailsService(appUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// CSRF
		http.csrf().disable();
		// l'authentification est faite par le header Authorization: Basic xxxx
		http.httpBasic();
		// le dossier [app] est accessible à tous
				http.authorizeRequests() //
						.antMatchers(HttpMethod.GET, "/app", "/app/**").permitAll();
		//Le serveur est Sans Etat : on accepte pas de session !
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//
		http.authorizeRequests() 
				.antMatchers("/projets").hasRole("EVALUATEUR")
				.antMatchers("/projets/*/collaborateurs").hasRole("COLLABORATEUR"); // ==> /projets/{id}/collaborateurs
				//.antMatchers("/", "/**") .hasRole("EVALUATEUR");
				//.antMatchers("/", "/**") .hasRole("ADMINISTRATEUR")
				//.antMatchers("/", "/**") .hasRole("COLLABORATEUR")
				//.antMatchers("/", "/**") .hasRole("MANAGERRH");*/
		
		
	//	  "/projets/**" ==> /projets/{id}/collabora/.........
		// deux etoile : 
	}
}
