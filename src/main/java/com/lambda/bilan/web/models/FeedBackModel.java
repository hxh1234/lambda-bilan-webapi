package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambda.bilan.domain.FeedBack;

public class FeedBackModel {

	private static Map<String, Object> getMapForFeedBack(FeedBack feedBack){
		if(feedBack==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("idCollaborateur",feedBack.getIntervention().getCollaborateur().getIdUtilisateur());
		hash.put("nomCollaborateur",feedBack.getIntervention().getCollaborateur().getNomUtilisateur()+" "+feedBack.getIntervention().getCollaborateur().getPrenomUtilisateur());
		hash.put("posteActuelCollaborateur",feedBack.getIntervention().getCollaborateur().getPosteActuelCollaborateur());
		hash.put("idProjet",feedBack.getIntervention().getProjet().getIdProjet());
		hash.put("nomProjet",feedBack.getIntervention().getProjet().getNomProjet());
		hash.put("dateIntervention",feedBack.getIntervention().getDateDebutIntervention()+" "+feedBack.getIntervention().getDateFinIntervention());
		hash.put("roleJoue",feedBack.getIntervention().getRoleJoue());
		hash.put("nombreJoursValorises",feedBack.getIntervention().getNombreJoursValorises());
		hash.put("notes",feedBack.getIntervention().getNotes());
		hash.put("totalePoids",feedBack.getTotalePoids());
		hash.put("nbrThemeQualifies",feedBack.getNbrThemeQualifies());
		hash.put("noteGlobale",feedBack.getNoteGlobale());
		return hash;
	}
	
	public static List<Map<String, Object>>  listeFeedBackRvised(List<FeedBack> list){
		List<Map<String, Object>> listeFeedBackRvised = new ArrayList<Map<String, Object>>();
		for (FeedBack feedBack : list) {
			listeFeedBackRvised.add(getMapForFeedBack(feedBack));
		}
		return listeFeedBackRvised;
	}
}
