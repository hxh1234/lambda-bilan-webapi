package com.lambda.bilan.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lambda.bilan.entities.Administrateur;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.ManagerRH;
import com.lambda.bilan.entities.Utilisateur;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IUtilisateurMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.RandomGenerator;
import com.lambda.bilan.web.helpers.PropretiesHelper;
import com.lambda.bilan.web.models.Reponse;
import com.lambda.bilan.web.models.UpdatePasswordModel;
import com.lambda.bilan.web.models.UtilisateurModel;

@RestController
public class UtilisateurController {

	@Autowired
	IUtilisateurMetier utilisateurMetier;


	/*
	 * Ajouter un utilisateur 
	 */
	//collaborateur
	@RequestMapping(value = "/collaborateurs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addColaborateur(@RequestBody Collaborateur collaborateur) {
		try {

			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			collaborateur.setPasswordUtilisateur(passwordUtilisateur);
			utilisateurMetier.addUtilisateur(collaborateur);
			//mailMetier.sendMailNewCollaborateur(collaborateur);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.add.success"));
	}

	//evaluateur
	@RequestMapping(value = "/evaluateurs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addEvaluateur(@RequestBody Evaluateur evaluateur) {
		try {

			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			evaluateur.setPasswordUtilisateur(passwordUtilisateur);
			utilisateurMetier.addUtilisateur(evaluateur);
			//sendmailmail(utilisateur);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.add.success"));
	}

	//managerRH
	@RequestMapping(value = "/managerRHs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addManagerRH(@RequestBody ManagerRH managerRH) {
		try {

			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			managerRH.setPasswordUtilisateur(passwordUtilisateur);
			utilisateurMetier.addUtilisateur(managerRH);
			//sendmailmail(utilisateur,PasswordUtilisateur);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.add.success"));
	}

	//administrateur
	@RequestMapping(value = "/administrateurs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addAdministrateur(@RequestBody Administrateur administrateur) {
		try {

			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			administrateur.setPasswordUtilisateur(passwordUtilisateur);
			utilisateurMetier.addUtilisateur(administrateur);
			//sendmailmail(utilisateur,PasswordUtilisateur);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.add.success"));
	}

	/*
	 * Mise a jour d'un utilisateur
	 */

	//collaborateur
	@RequestMapping(value = "/collaborateurs/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateCollaborateur(@PathVariable("id") Long id, @RequestBody Collaborateur collaborateur)  {
		try {
			if (collaborateur.getIdUtilisateur().equals(id))
				utilisateurMetier.updateUtilisateur(collaborateur);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.success"));
	}

	//evaluateur
	@RequestMapping(value = "/evaluateurs/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateEvaluateur(@PathVariable("id") Long id, @RequestBody Evaluateur evaluateur)  {
		try {
			if(evaluateur.getIdUtilisateur().equals(id))
				utilisateurMetier.updateUtilisateur(evaluateur);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.success"));
	}

	//managerRH
	@RequestMapping(value = "/managerRHs/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateManagerRH(@PathVariable("id") Long id, @RequestBody ManagerRH managerRH)  {
		try {
			if(managerRH.getIdUtilisateur().equals(id))
				utilisateurMetier.updateUtilisateur(managerRH);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.success"));
	}

	//administrateur
	@RequestMapping(value = "/administrateurs/{id}", method = RequestMethod.PUT, consumes = "application/json; charset=UTF-8")
	public Reponse updateAdministrateur(@PathVariable("id") Long id, @RequestBody Administrateur administrateur)  {
		try {
			if(administrateur.getIdUtilisateur().equals(id))
				utilisateurMetier.updateUtilisateur(administrateur);
			else
				return new Reponse(0, PropretiesHelper.getText("general.update.fail"));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.success"));
	}

	/*
	 * obtenir un utilisateur
	 */
	@RequestMapping(value="/utilisateurs/{id}",method=RequestMethod.GET)
	public Reponse getUtilisateur(@PathVariable("id") Long id){
		try {
			return new Reponse(0,utilisateurMetier.getUtilisateur(id));
		} catch (Exception e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * Suppression d'un utilisateur
	 */

	@RequestMapping(value = "/utilisateurs/{id}", method = RequestMethod.DELETE)
	public Reponse deleteUtilisateur(@PathVariable("id") Long id) {
		try {
			utilisateurMetier.deleteUtilisateur(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.delete.success"));
	}


	/*
	 * mise a jour du mot de passe
	 */
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

	/*
	 * depart d'un collaborateur
	 */
	@RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.PUT)
	public Reponse departCollaborateur(@PathVariable("id") Long id) {
		try {
			utilisateurMetier.departCollaborateur(id);
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
		return new Reponse(0, PropretiesHelper.getText("utilisateur.update.success"));
	}


	/*
	 * Liste des utilisateurs
	 */
	@RequestMapping(value = "/utilisateurs" , method = RequestMethod.GET)
	public Reponse getAllUtilisateur()  {
		try {
			return new Reponse(0,UtilisateurModel.listeUtilisateurRvised(utilisateurMetier.getAllUtilisateur()));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * liste des collaborateurs abondonner
	 */
	@RequestMapping(value = "/oldcollaborateurs" , method = RequestMethod.GET)
	public Reponse getAllOldCollaborateur()  {
		try {
			return new Reponse(0,utilisateurMetier.getAllOldCollaborateur());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * liste des managerRH
	 */
	@RequestMapping(value = "/managerRHs" , method = RequestMethod.GET)
	public Reponse getAllManagerRH()  {
		try {
			return new Reponse(0,utilisateurMetier.getAllManagerRH());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * liste des collaborateurs sans projets
	 */
	@RequestMapping(value="/collaborateurs_without_projet", method=RequestMethod.GET)
	public Reponse getAllCollaborateurWithoutProjet(){
		try {
			return new Reponse(0,utilisateurMetier.getAllCollaborateurWithoutProject());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	/*
	 * liste des collaborateurs sans Objectifs
	 */
	@RequestMapping(value="/collaborateurs_without_objectif" , method = RequestMethod.GET)
	public Reponse getAllCollaborateurWithoutObjectif(){
		try {
			return new Reponse(0,utilisateurMetier.getAllCollaborateurWithoutObjectif());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

}
