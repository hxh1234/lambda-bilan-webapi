package com.lambda.bilan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IProjetMetier;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.ProjetModel;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class ProjetController {
	
	@Autowired
	IProjetMetier projetMetier;
	
	/*
	 * ajouter un projet *
	 */
	@RequestMapping(value = "/projets", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addProjet(@RequestBody Projet projet) {
		try {
			projetMetier.addProjet(projet);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("projet.add.success"));
	}

	/*
	 * mise a jour du projet *
	 */
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

	/*
	 * supprimer un projet *
	 */
	@RequestMapping(value = "/projets/{id}", method = RequestMethod.DELETE)
	public Reponse deleteProjet(@PathVariable("id") Long id) {
		try {
			projetMetier.deleteProjet(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("projet.delete.success"));
	}
	
	/*
	 * list des projets *
	 */
	@RequestMapping(value = "/projets" , method = RequestMethod.GET)
	public Reponse getAllProjet(){
		try {
			return new Reponse(0,ProjetModel.listeProjetRvised(projetMetier.getAllProjet()));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * liste des projet pour un evaluateur *
	 */
	@RequestMapping(value = "/evaluateurs/{id}/projets" , method = RequestMethod.GET)
	public Reponse getAllProjetOfEvaluateur(@PathVariable("id") Long id){
		try {
			Evaluateur evaluateur = new Evaluateur(id);
			return new Reponse(0,projetMetier.getAllProjetOfEvaluateur(evaluateur));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * liste des projet pour un collaborateur *
	 */
	@RequestMapping(value = "/collaborateurs/{id}/projets" , method = RequestMethod.GET)
	public Reponse getAllProjetOfCollaborateur(@PathVariable("id") Long id){
		try {
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,ProjetModel.listeProjetRvised(projetMetier.getAllProjetOfCollaborateur((collaborateur))));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
}
