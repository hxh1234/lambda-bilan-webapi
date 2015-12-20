package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IInterventionMetier;

@Component
public class CollaborateurModel {
	@Autowired
	IInterventionMetier interventionMetier;


	public List<Map<String, Object>> listeCollaborateurRvised(List<Collaborateur> collaborateurs, Long idProjet) throws LambdaException{
		List<Map<String, Object>> listeCollaborateurRvised = new ArrayList<Map<String, Object>>();
		for (Collaborateur collaborateur : collaborateurs) {
			listeCollaborateurRvised.add(getMapForCollaborateur(collaborateur, idProjet));
		}
		return listeCollaborateurRvised;
	}
	
	private  Map<String, Object> getMapForCollaborateur(Collaborateur collaborateur, Long idProjet) throws LambdaException{
		if(collaborateur==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();		
		hash.put("idUtilisateur",collaborateur.getIdUtilisateur());
		hash.put("nomUtilisateur",collaborateur.getNomUtilisateur());
		hash.put("prenomUtilisateur",collaborateur.getPrenomUtilisateur());
		hash.put("telephoneUtilisateur",collaborateur.getTelephoneUtilisateur());
		Intervention intervention =interventionMetier.getIntervention(idProjet, collaborateur.getIdUtilisateur());
		if(intervention.getDateFinIntervention()==null)
			hash.put("option",0);
		else if(intervention.getDateFinIntervention().before(new Date()))
			hash.put("option",1);
		else
			hash.put("option",2);
		return hash;
	}
	
}
