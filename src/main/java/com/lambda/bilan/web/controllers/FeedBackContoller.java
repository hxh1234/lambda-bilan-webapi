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
import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.entities.Note;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IFeedBackMetier;
import com.lambda.bilan.metier.IInterventionMetier;
import com.lambda.bilan.metier.InterventionMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.FeedBackModel;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class FeedBackContoller {

	@Autowired
	IFeedBackMetier feedBackMetier;
	@Autowired
	IInterventionMetier interventionMetier;
	
	/*
	 * liste des feedback d'un collaborateur
	 */
	@RequestMapping(value = "/collaborateurs/{id}/feedBacks" , method = RequestMethod.GET)
	public Reponse getAllfeedBackOfCollaborateurByYear(@PathVariable("id") Long id,Date year) {
		try {
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,FeedBackModel.listeFeedBackRvised(feedBackMetier.getAllfeedBackOfCollaborateurByYear(collaborateur, year)));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	/*
	 * Cree Feedback
	 */
	@RequestMapping(value="/feedbacks",method=RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse assignProjet(@RequestBody List<Note> notes){
		try {
			feedBackMetier.addFeedBack(notes);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("feedback.add.success"));
	}
	
	/*
	 * Valider feedback 
	 */
	@RequestMapping(value = "/feedbacks/{id}", method = RequestMethod.PUT)
	public Reponse validerFeedBack(@PathVariable("id") Long idIntervention) {
		try {
			feedBackMetier.validerFeedBack(idIntervention);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("feedback.valide.success"));
	}
}
