package com.lambda.bilan.web.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.lambda.bilan.dao.UserDAO;
import com.lambda.bilan.entities.Utilisateur;

public class Test {
	
	@Autowired
	UserDAO dao ;
	
	public void getuser(){
		Utilisateur utilisateur=new Utilisateur();
		utilisateur.setNomUtilisateur("anas");
		dao.save(utilisateur);
		System.out.println(dao.findOne(1L).getNomUtilisateur());
		
	}

}
