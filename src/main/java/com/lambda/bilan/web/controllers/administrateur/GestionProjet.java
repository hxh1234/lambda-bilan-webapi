package com.lambda.bilan.web.controllers.administrateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;
import com.lambda.bilan.metier.IProjetMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class GestionProjet {

	@Autowired
	IProjetMetier projetMetier;
	
	
	@RequestMapping(value = "/projets", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addProjet(@RequestBody Projet projet) {
		try {
			projetMetier.addProjet(projet);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("projet.add.success"));
	}

	@RequestMapping(value = "/projets" , method = RequestMethod.GET)
	public Reponse getAllProjet(){
		try {
			return new Reponse(0,projetMetier.getAllProjet());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	
	@RequestMapping(value = "/projets/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateProjet(@PathVariable  ("id") Long id, @RequestBody Projet projet){
		try {
			if (projet.getIdProjet().equals(id))
				projetMetier.updateProjet(projet);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("projet.update.success"));
	}

	@RequestMapping(value = "/projets/{id}", method = RequestMethod.DELETE)
	public Reponse deleteProjet(@PathVariable("id") Long id) {
		try {
			projetMetier.deleteProjet(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("projet.delete.success"));
	}
}
