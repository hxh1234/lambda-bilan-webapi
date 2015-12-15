package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Utilisateur;

public class UtilisateurModel {
	private final static String MANAGERRH_CLASS="com.lambda.bilan.entities.ManagerRH";
	private final static String COLLABORATEUR_CLASS="com.lambda.bilan.entities.Collaborateur";
	private final static String EVALUATEUR_CLASS="com.lambda.bilan.entities.Evaluateur";
	private final static String ADMINISTRATEUR_CLASS="com.lambda.bilan.entities.Administrateur";
	
	private static Map<String, Object> getMapForUtilisateur(Utilisateur utilisateur){
		if(utilisateur==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();		
		hash.put("idUtilisateur",utilisateur.getIdUtilisateur() );
		hash.put("urlPhotoUtilisateur", utilisateur.getUrlPhotoUtilisateur());
		hash.put("passwordUtilisateur",utilisateur.getPasswordUtilisateur());
		hash.put("telephoneUtilisateur",utilisateur.getTelephoneUtilisateur());
		hash.put("sexeUtilisateur",utilisateur.getSexeUtilisateur());
		hash.put("nomUtilisateur",utilisateur.getNomUtilisateur());
		hash.put("prenomUtilisateur",utilisateur.getPrenomUtilisateur());
		hash.put("emailUtilisateur",utilisateur.getEmailUtilisateur());
		hash.put("dateNaissanceUtilisateur",utilisateur.getDateNaissanceUtilisateur());
		hash.put("adresseUtilisateur",utilisateur.getAdresseUtilisateur());
		hash.put("idCalendrierUtilisateur",utilisateur.getIdCalendrierUtilisateur());
		if(utilisateur.getClass().getName()==COLLABORATEUR_CLASS){
			Collaborateur collaborateur=(Collaborateur) utilisateur;
			hash.put("idManagerRH",collaborateur.getIdManagerRH());
			hash.put("dateDepartCollaborateur",collaborateur.getDateDepartCollaborateur());
			hash.put("dateEmbaucheCollaborateur",collaborateur.getDateEmbaucheCollaborateur());
			hash.put("etatCollaborateur",collaborateur.getEtatCollaborateur());
			hash.put("posteActuelCollaborateur",collaborateur.getPosteActuelCollaborateur());
			hash.put("role", "Collaborateur");
		}
		if(utilisateur.getClass().getName()==MANAGERRH_CLASS){
			hash.put("role", "ManagerRH");
		}
		if(utilisateur.getClass().getName()==ADMINISTRATEUR_CLASS){
			hash.put("role", "Administrateur");
		}
		if(utilisateur.getClass().getName()==EVALUATEUR_CLASS){
			hash.put("role", "evaluateur");
		}
		return hash;
	}
	
	public static List<Map<String, Object>> listeUtilisateurRvised(List<Utilisateur> utilisateurs){
		List<Map<String, Object>> listeUtilisateurRvised = new ArrayList<Map<String, Object>>();
		for (Utilisateur utilisateur : utilisateurs) {
			listeUtilisateurRvised.add(getMapForUtilisateur(utilisateur));
		}
		return listeUtilisateurRvised;
	}
			

		
	

}
