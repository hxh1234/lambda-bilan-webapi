package com.lambda.bilan.web.controllers;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	/*
	 * date serveur
	 */
	@RequestMapping(value="/date_serveur" , method = RequestMethod.GET)
	public Date getDateServeur(){
		return new Date(Calendar.getInstance().getTime().getTime());
	}
	
}
