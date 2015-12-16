package com.lambda.bilan.web.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Objectif;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IObjectifMetier;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class ObjectifController {
	
	@Autowired
	IObjectifMetier objectifMetier;
	
	/*
	 * fiche d'objectifs
	 */
	@RequestMapping(value = "/collaborateurs/{id}/ficheObjectifs" , method = RequestMethod.GET)
	public Reponse getFicheObjectifsOfCollaborateurByYear(@PathVariable("id") Long id ,Date year) {
		try {
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,objectifMetier.getFicheObjectifsOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * liste des objectifs refusé
	 */
	@RequestMapping(value="/objectifs_refus/{id}" , method = RequestMethod.GET)
	public Reponse getAllObjectifsRefusFromCollaborateurOfManagerRH(@PathVariable("id") Long id){
		try {
			return new Reponse(0,objectifMetier.getAllObjectifsRefusFromCollaborateurOfManagerRH(id));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * liste des categories
	 */
	@RequestMapping(value="/categories" , method = RequestMethod.GET)
	public Reponse getAllCategorie(){
		try {
			return new Reponse(0,objectifMetier.getAllCategorie());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * Ajouter des objectifs
	 */
	@RequestMapping(value = "/objectifs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addColaborateur(@RequestBody List<Objectif> objectifs) {
		try {
			objectifMetier.addObjectif(objectifs);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0,PropretiesHelper.getText("objectif.add.success"));
	}
	
	/*
	 * Mise à jour Objectif
	 */
	//Mise à jour les mesures aussi
	@RequestMapping(value = "/objectifs/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateCollaborateur(@PathVariable("id") Long id, @RequestBody Objectif objectif)  {
		try {
			if (objectif.getIdObjectif().equals(id))
				objectifMetier.updateObjectif(objectif);
			else
				return new Reponse(0, PropretiesHelper.getText("objectif.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("objectif.update.success"));
	}
	
	
}
