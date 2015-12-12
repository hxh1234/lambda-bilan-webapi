package com.lambda.bilan.web.helpers;

import java.util.HashMap;
import java.util.Map;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Utilisateur;

public class MapforUtilisateur {
	
	public static Map<String, Object> getMapForUtilisateur(Utilisateur utilisateur){
		if(utilisateur==null)
			return null;

		Map<String, Object> hash = new HashMap<String, Object>();		
		hash.put("idObjectif", ((Collaborateur)utilisateur).getManagerRH().getIdUtilisateur());
		
		return null;
	}
}
