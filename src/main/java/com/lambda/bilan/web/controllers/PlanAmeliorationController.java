package com.lambda.bilan.web.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.domain.PlanAmelioration;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IPlanAmeliorationMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class PlanAmeliorationController {
	
	@Autowired
	IPlanAmeliorationMetier planAmeliorationMetier;
	
	/*
	 * plan Amelioration d'un collaborateur
	 */
	@RequestMapping(value = "/collaborateurs/{id}/planAmeliorations" , method = RequestMethod.GET)
	public Reponse getPlanAmeliorationOfCollaborateurByYear(@PathVariable("id") Long id ,Date year) {
		try {	
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,planAmeliorationMetier.getPlanAmeliorationOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * Ajouter un plan d'Amelioration pour un collaborateur
	 */
	
	@RequestMapping(value = "/planAmeliorations", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addColaborateur(@RequestBody PlanAmelioration planAmelioration) {
		try {
			planAmeliorationMetier.addPlanAmelioration(planAmelioration);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("planAmelioration.add.success"));
	}

}
