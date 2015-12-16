package com.lambda.bilan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IBAPMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class BAPController {
	
	@Autowired
	IBAPMetier bapMetier;
	
	/*
	 * list des BAP d'un collaborateur
	 */
	@RequestMapping(value = "/collaborateurs/{id}/bap" , method = RequestMethod.GET)
	public Reponse getAllBAPOfCollaborateur(@PathVariable("id") Long id) {
		try {
			return new Reponse(0,bapMetier.getAllBAPOfCollaborateur(id));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

}
