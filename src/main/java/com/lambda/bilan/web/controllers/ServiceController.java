package com.lambda.bilan.web.controllers;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.web.models.Reponse;

@RestController
public class ServiceController {

	/*
	 * date serveur
	 */
	@RequestMapping(value="/date_serveur" , method = RequestMethod.GET)
	public Reponse getDateServeur(){
		return new Reponse(0,new Date(Calendar.getInstance().getTime().getTime()));
	}
	
}
