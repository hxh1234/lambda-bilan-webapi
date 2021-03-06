package com.lambda.bilan.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IMesureMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class MesureContoller {

	@Autowired
	IMesureMetier mesureMetier;
	
	/*
	 * Ajouter Mesure *
	 */
	@RequestMapping(value = "/mesures", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addMesure(@RequestBody List<Mesure> mesures) {
		try {
			if(mesures.size()>1)
				mesureMetier.addMesure(mesures);
			else{
				Mesure mesure =mesures.get(0);
				mesureMetier.addMesure(mesure);
			}
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("mesure.add.success"));
	}
	
	/*
	 * mise a jour Mesure*
	 */
	@RequestMapping(value = "/mesures/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateMesure(@PathVariable("id") Long id, @RequestBody Mesure mesure)  {
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
	 * Suppression Mesure *
	 */

	@RequestMapping(value = "/mesures/{id}", method = RequestMethod.DELETE)
	public Reponse deleteMesure(@PathVariable("id") Long id) {
		try {
			mesureMetier.deleteMesure(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("mesure.delete.success"));
	}

	/*
	 * Liste Responsable Mesure *
	 */
	@RequestMapping(value = "/responsables_mesures", method = RequestMethod.GET)
	public Reponse getAllResponsableMesure() {
		try {
			return new Reponse(0, mesureMetier.getAllResponsableMesure());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
}
