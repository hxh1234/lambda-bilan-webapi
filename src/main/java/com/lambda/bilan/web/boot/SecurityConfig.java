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
				.antMatchers("/projets/*/collaborateurs").hasRole("COLLABORATEUR") // ==> /projets/{id}/collaborateurs
				.antMatchers("/utilisateurs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.DELETE,"/utilisateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/utilisateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.GET,"/utilisateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.POST,"/collaborateurs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.POST,"/evaluateurs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.POST,"/managerRHs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.POST,"/administrateurs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/collaborateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/evaluateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/managerRHs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/administrateurs/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.GET,"/managerRHs").hasRole("ADMINISTRATEUR")
				/**ADMINE : Gestion Projet**/
				.antMatchers(HttpMethod.GET,"/projets").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.GET,"/evaluateurs").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.POST,"/projets").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.PUT,"/projets/*").hasRole("ADMINISTRATEUR")
				.antMatchers(HttpMethod.DELETE,"/projets/*").hasRole("ADMINISTRATEUR")
				/**MANAGER RH: Accueuil**/
				.antMatchers(HttpMethod.GET,"/collaborateurs_without_projet").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/collaborateurs_without_objectif").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/objectifs_refus/*").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.POST,"/interventions").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/projets").hasRole("MANAGERRH") //
				.antMatchers(HttpMethod.GET,"/categories").hasRole("MANAGERRH") 
				.antMatchers(HttpMethod.POST,"/objectifs").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.PUT,"/objectifs/*").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/objectifs/*").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.DELETE,"/objectifs/*").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/categories").hasRole("MANAGERRH") 
				.antMatchers(HttpMethod.PUT,"/objectifs_valider/*").hasRole("MANAGERRH") 
				/**MANAGER RH :Liste collaborateur**/
				.antMatchers(HttpMethod.GET,"/managerRHs/*/collaborateurs").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.POST,"/planAmeliorations").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/collaborateurs/*/objectifs").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/responsables_mesures").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.POST,"/objectifs_evaluation").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.POST,"/mesures").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.PUT,"/mesures/*").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.DELETE,"/mesures/*").hasRole("MANAGERRH")
				/**MANAGER RH :Blan de performance**/
				.antMatchers(HttpMethod.GET,"/collaborateurs/*/baps").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/collaborateurs/*/feedBacks").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/collaborateurs/*/ficheObjectifs").hasRole("MANAGERRH")
				.antMatchers(HttpMethod.GET,"/collaborateurs/*/planAmeliorations").hasRole("MANAGERRH")
				/**Evaluateur : lise projet**/
				.antMatchers(HttpMethod.GET,"/evaluateurs/*/projets").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.GET,"/projets/*/collaborateurs").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.PUT,"/interventions/*/*").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.GET,"/interventions/*/*").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.POST,"/feedbacks").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.GET,"/themes").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.GET,"/qualifications").hasRole("EVALUATEUR")
				.antMatchers(HttpMethod.PUT,"/feedbacks/*").hasRole("EVALUATEUR")
				/**Collaborateur **/
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				;
		
		
		
				//.antMatchers("/", "/**") .hasRole("EVALUATEUR");
				//.antMatchers("/", "/**") .hasRole("ADMINISTRATEUR")
				//.antMatchers("/", "/**") .hasRole("COLLABORATEUR")
				//.antMatchers("/", "/**") .hasRole("MANAGERRH");*/
		
		
	//	  "/projets/**" ==> /projets/{id}/collabora/.........
		// deux etoile : 
	}
}
