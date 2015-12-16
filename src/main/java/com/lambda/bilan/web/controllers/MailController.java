package com.lambda.bilan.web.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class MailController {
	
	/*
	 * Envoyer un mail
	 */
	@RequestMapping(value = "/mails", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse sendMail(@RequestBody String mail /*Objet mail */  ) {
		/*try {

		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}*/
		return new Reponse(0, PropretiesHelper.getText("utilisateur.email.success"));
	}

}
