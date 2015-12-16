package com.lambda.bilan.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IInterventionMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class InterventionController {
	
	@Autowired
	IInterventionMetier interventionMetier;
	
	/*
	 * assignation des projets au collaborateur
	 */
	@RequestMapping(value="/interventions",method=RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse assignProjet(@RequestBody List<Intervention>interventions){
		try {
			interventionMetier.assignProjet(interventions);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("intervention.add.success"));
	}

}
