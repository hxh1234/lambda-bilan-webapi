package com.lambda.bilan.web.controllers.administrateur;

import java.sql.Date;

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
import com.lambda.bilan.helpers.PropretiesHelper;
import com.lambda.bilan.metier.IBAPMetier;
import com.lambda.bilan.metier.IFeedBackMetier;
import com.lambda.bilan.metier.IObjectifMetier;
import com.lambda.bilan.metier.IPlanAmeliorationMetier;
import com.lambda.bilan.metier.IUtilisateurMetier;
import com.lambda.bilan.web.helpers.ExceptionHelpers;
import com.lambda.bilan.web.helpers.RandomGenerator;
import com.lambda.bilan.web.models.UtilisateurModel;
import com.lambda.bilan.web.models.Reponse;

@RestController
public class GestionUtilisateur{

	@Autowired
	IUtilisateurMetier utilisateurMetier;
	@Autowired
	IBAPMetier bapMetier;
	@Autowired
	IFeedBackMetier feedBackMetier;
	@Autowired
	IObjectifMetier objectifMetier;
	@Autowired
	IPlanAmeliorationMetier planAmeliorationMetier;


	/*
	 * Ajouter les utilisateurs 
	 */

	//collaborateur
	@RequestMapping(value = "/collaborateurs", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8")
	public Reponse addColaborateur(@RequestBody Collaborateur collaborateur) {
		try {

			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			collaborateur.setPasswordUtilisateur(passwordUtilisateur);
			utilisateurMetier.addUtilisateur(collaborateur);
			//sendmailmail(utilisateur,PasswordUtilisateur);
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
			//sendmailmail(utilisateur,PasswordUtilisateur);
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
	 * Mise a jour des utilisateurs
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
	 * Liste de {methode}
	 */
	@RequestMapping(value = "/utilisateurs" , method = RequestMethod.GET)
	public Reponse getAllUtilisateur()  {
		try {
			return new Reponse(0,UtilisateurModel.listeUtilisateurRvised(utilisateurMetier.getAllUtilisateur()));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/oldcollaborateurs" , method = RequestMethod.GET)
	public Reponse getAllOldCollaborateur()  {
		try {
			return new Reponse(0,utilisateurMetier.getAllOldCollaborateur());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/managerRHs" , method = RequestMethod.GET)
	public Reponse getAllManagerRH()  {
		try {
			return new Reponse(0,utilisateurMetier.getAllManagerRH());
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/collaborateurs/{id}/bap" , method = RequestMethod.GET)
	public Reponse getAllBAPOfCollaborateur(@PathVariable("id") Long id) {
		try {
			return new Reponse(0,bapMetier.getAllBAPOfCollaborateur(id));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/collaborateurs/{id}/feedBack" , method = RequestMethod.GET)
	public Reponse getAllfeedBackOfCollaborateurByYear(@PathVariable("id") Long id,Date year) {
		try {
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,feedBackMetier.getAllfeedBackOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/collaborateurs/{id}/ficheObjectifs" , method = RequestMethod.GET)
	public Reponse getFicheObjectifsOfCollaborateurByYear(@PathVariable("id") Long id ,Date year) {
		try {
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,objectifMetier.getFicheObjectifsOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}

	@RequestMapping(value = "/collaborateurs/{id}/planAmelioration" , method = RequestMethod.GET)
	public Reponse getPlanAmeliorationOfCollaborateurByYear(@PathVariable("id") Long id ,Date year) {
		try {	
			Collaborateur collaborateur = new Collaborateur(id);
			return new Reponse(0,planAmeliorationMetier.getPlanAmeliorationOfCollaborateurByYear(collaborateur, year));
		} catch (LambdaException e) {
			return new Reponse(1,ExceptionHelpers.getErreursForException(e));
		}
	}


}
