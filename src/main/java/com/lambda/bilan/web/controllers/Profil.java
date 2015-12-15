package com.lambda.bilan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.entities.Utilisateur;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;
import com.lambda.bilan.metier.IUtilisateurMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.models.UpdatePasswordModel;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class Profil {

	@Autowired
	IUtilisateurMetier utilisateurMetier;
	
	@RequestMapping(value = "/profils/{id}" , method = RequestMethod.GET)
	public Reponse getProfil(@PathVariable("id") Long id){
		try {
			return new Reponse(0,utilisateurMetier.getUtilisateur(id));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}
	
	@RequestMapping(value = "/profils" , method = RequestMethod.PUT , consumes = "application/json; charset=UTF-8")
	public Reponse updatepassword(@RequestBody UpdatePasswordModel getUpdatePassword){
		try {
			Utilisateur utilisateur = utilisateurMetier.getUtilisateur(getUpdatePassword.getIdUtilisateur());
			if(utilisateur.getPasswordUtilisateur().equals(getUpdatePassword.getCurrentPassword()))
				utilisateurMetier.updatePassword(getUpdatePassword.getNewPassword(),getUpdatePassword.getIdUtilisateur());
		} catch (Exception e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.password.success"));
	}
}
