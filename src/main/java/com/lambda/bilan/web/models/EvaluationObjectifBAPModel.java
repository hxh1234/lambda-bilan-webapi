package com.lambda.bilan.web.models;

import java.util.List;

import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.entities.Objectif;

public class EvaluationObjectifBAPModel {

	private List<Mesure> mesures;
	private List<Objectif> objectifs;
	
	public EvaluationObjectifBAPModel() {
		super();
	}

	public List<Mesure> getMesures() {
		return mesures;
	}

	public void setMesures(List<Mesure> mesures) {
		this.mesures = mesures;
	}

	public List<Objectif> getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(List<Objectif> objectifs) {
		this.objectifs = objectifs;
	}
	
}
