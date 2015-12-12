package com.lambda.bilan.web.controllers.administrateur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.metier.IProjetMetier;

@Component
public class GestionProjet {

	@Autowired
	IProjetMetier projetMetier;
	
	
	public void addProjet(Projet projet) throws LambdaException {
		projetMetier.addProjet(projet);
	}

	
	public List<Projet> getAllProjet() throws LambdaException {
		return projetMetier.getAllProjet();
	}

	
	public void updateProjet(Projet projet) throws LambdaException {
		projetMetier.updateProjet(projet);
	}

	
	public void deleteProjet(Long id) throws LambdaException {
		projetMetier.deleteProjet(id);
	}

	
}
