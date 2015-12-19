package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambda.bilan.entities.Objectif;

public class ObjectifModel {
	
	
	public static List<Map<String, Object>> listeObjectifRvised(List<Objectif> objectifs){
		List<Map<String, Object>> listeObjectifRvised = new ArrayList<Map<String, Object>>();
		for (Objectif objectif : objectifs) {
			listeObjectifRvised.add(getMapForObjectif(objectif));
		}
		return listeObjectifRvised;
	}
	
	
	private static Map<String, Object> getMapForObjectif(Objectif objectif){
		if(objectif==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();		
		hash.put("idObjectif",objectif.getIdObjectif() );
		hash.put("nomObjectif",objectif.getNomObjectif() );
		hash.put("descriptifObjectif",objectif.getDescriptifObjectif());		
		return hash;
	}

}
