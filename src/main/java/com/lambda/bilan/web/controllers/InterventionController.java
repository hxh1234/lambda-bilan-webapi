package com.lambda.bilan.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	/*
	 * Liste des interventions fini
	 */
	@RequestMapping(value="/intervention_fini/{id}" , method = RequestMethod.GET)
	public Reponse getTop6FinishedInterventionOfEvaluateur(@PathVariable("id") Long id){
		try {
			return new Reponse(0,interventionMetier.getTop6FinishedInterventionOfEvaluateur(id));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * Obtenir une intervention
	 */
	@RequestMapping(value="/intervention/{idProjet}/{idCollaborateur}",method=RequestMethod.GET)
	public Reponse getIntervention(@PathVariable("idProjet") Long idProjet,@PathVariable("idCollaborateur") Long idCollaborateur){
		try {
			return new Reponse(0,interventionMetier.getIntervention(idProjet, idCollaborateur));
		} catch (Exception e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * definir intervantion (par un Evaluateur)
	 */
	@RequestMapping(value = "/interventions/{idProjet}/{idCollaborateur}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse defineIntervention(@PathVariable("idProjet") Long idProjet,@PathVariable("idCollaborateur") Long idCollaborateur, @RequestBody Intervention intervention)  {
		try {
			intervention.setIdIntervention(interventionMetier.getIntervention(idProjet, idCollaborateur).getIdIntervention());
			interventionMetier.defineIntervention(intervention);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("intervention.update.success"));
	}

	/*
	 * liste des themes
	 */
	@RequestMapping(value="/themes" , method = RequestMethod.GET)
	public Reponse getAllTheme(){
		try {
			return new Reponse(0,interventionMetier.getAllTheme());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * liste des qualifications
	 */
	@RequestMapping(value="/qualifications" , method = RequestMethod.GET)
	public Reponse getAllQualification(){
		try {
			return new Reponse(0,interventionMetier.getAllQualification());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * Creer feedback
	 */

}
