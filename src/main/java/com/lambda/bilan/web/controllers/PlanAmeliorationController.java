package com.lambda.bilan.web.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IPlanAmeliorationMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class PlanAmeliorationController {
	
	@Autowired
	IPlanAmeliorationMetier planAmeliorationMetier;
	
	/*
	 * plan Amelioration d'un collaborateur
	 */
	@RequestMapping(value = "/collaborateurs/{id}/planAmelioration" , method = RequestMethod.GET)
	public Reponse getPlanAmeliorationOfCollaborateurByYear(@PathVariable("id") Long id ,Date year) {
		try {	
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,planAmeliorationMetier.getPlanAmeliorationOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

}
