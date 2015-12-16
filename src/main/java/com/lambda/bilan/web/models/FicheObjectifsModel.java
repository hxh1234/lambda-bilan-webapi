package com.lambda.bilan.web.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lambda.bilan.domain.FicheObjectifs;
import com.lambda.bilan.entities.Objectif;

public class FicheObjectifsModel {

	public static Map<String, Object> getMapForFicheObjectifs(FicheObjectifs ficheObjectifs){
		if(ficheObjectifs==null)
			return null;
		Map<String, Object> hash = new HashMap<String, Object>();
		List<Map<String, Object>> objectifRvised = new ArrayList<Map<String, Object>>();
		Map<String, Object> e;
		
		for (Objectif objectif : ficheObjectifs.getObjectifs()) {
			e = new HashMap<String, Object>();
			e.put("categorie", objectif.getCategorie().getNomCategorie());
			e.put("nomObjectif", objectif.getNomObjectif());
			e.put("descriptionObjectif", objectif.getDescriptifObjectif());
			e.put("mesures", objectif.getMesures());
			e.put("nomCollaborateur", objectif.getCollaborateur().getNomUtilisateur()+" "+objectif.getCollaborateur().getPrenomUtilisateur());
			objectifRvised.add(e);
		}
		
		hash.put("objectifs", objectifRvised);
		hash.put("noteFinal",ficheObjectifs.getNoteFinal());
		return hash;
	}
}
