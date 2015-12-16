package com.lambda.bilan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IMesureMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.helpers.RandomGenerator;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class MesureContoller {

	@Autowired
	IMesureMetier mesureMetier;
	
	/*
	 * Ajouter Mesure
	 */
	@RequestMapping(value = "/mesures", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addColaborateur(@RequestBody Mesure mesure) {
		try {
			mesureMetier.addMesure(mesure);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("mesure.add.success"));
	}
	
	/*
	 * mise a jour Mesure
	 */
	@RequestMapping(value = "/mesures/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateCollaborateur(@PathVariable("id") Long id, @RequestBody Mesure mesure)  {
		try {
			if (mesure.getIdMesure().equals(id))
				mesureMetier.updateMesure(mesure);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("mesure.update.success"));
	}
	
	/*
	 * Suppression Mesure
	 */

	@RequestMapping(value = "/mesures/{id}", method = RequestMethod.DELETE)
	public Reponse deleteUtilisateur(@PathVariable("id") Long id) {
		try {
			mesureMetier.deleteMesure(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("mesure.delete.success"));
	}

}
