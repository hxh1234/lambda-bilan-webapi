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
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class ObjectifController {
	
	@Autowired
	IObjectifMetier objectifMetier;
	
	
	/*
	 * Ajouter des objectifs
	 */
	@RequestMapping(value = "/objectifs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addObjectif(@RequestBody List<Objectif> objectifs) {
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
	public Reponse updateObjectif(@PathVariable("id") Long id, @RequestBody Objectif objectif)  {
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
	
	/*
	 * Supression Objectif
	 */
	@RequestMapping(value = "/objectifs/{id}", method = RequestMethod.DELETE)
	public Reponse deleteObjectif(@PathVariable("id") Long id) {
		try {
			objectifMetier.deleteObjectif(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("objectif.delete.success"));
	}
	
	/*
	 * obtenir une objectif
	 */
	@RequestMapping(value="/objectifs/{id}",method=RequestMethod.GET)
	public Reponse getUtilisateur(@PathVariable("id") Long id){
		try {
			return new Reponse(0,objectifMetier.getObjectif(id));
		} catch (Exception e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * Valider l'objectif
	 */
	@RequestMapping(value = "/objectifs_valider/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse validerObjectif(@PathVariable("id") Long id)  {
		try {
				objectifMetier.validerObjectif(id);	
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("objectif.update.success"));
	}
	
	/*
	 * Refuser l'objectif
	 */
	@RequestMapping(value = "/objectifs_refuser/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse refuserObjectif(@PathVariable("id") Long id)  {
		try {
				objectifMetier.refuserObjectif(id);	
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("objectif.update.success"));
	}
	
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
	
	
	
}
