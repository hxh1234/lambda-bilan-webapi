package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambda.bilan.entities.Projet;

public class ProjetModel {
	
	private static Map<String, Object> getMapForProjet(Projet projet){
		if(projet==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("idProjet", projet.getIdProjet());
		hash.put("nomProjet", projet.getNomProjet());
		hash.put("idEvaluateur", projet.getEvaluateur().getIdUtilisateur());
		hash.put("nomEvaluateur", projet.getEvaluateur().getNomUtilisateur());
		hash.put("urlPhotoEvaluateur", projet.getEvaluateur().getUrlPhotoUtilisateur());
		return hash;
	}
	
	public static List<Map<String, Object>>  listeProjetRvised(List<Projet> list){
		List<Map<String, Object>> listeProjetRvised = new ArrayList<Map<String, Object>>();
		for (Projet projet : list) {
			listeProjetRvised.add(getMapForProjet(projet));
		}
		return listeProjetRvised;
	}


}
