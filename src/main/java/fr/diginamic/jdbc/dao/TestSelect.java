package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;
import fr.diginamic.jdbc.exceptions.DaoException;

public class TestSelect {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		List<Fournisseur> listeFournisseurs = new ArrayList<>();
		try {
			listeFournisseurs = daoF.extraire();
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		//Affichage de la liste des fournisseurs
		System.out.println("ID\tNOM");
		for(Fournisseur f : listeFournisseurs) {
			System.out.println(f.getId()+"\t"+f.getNom());
		}
	
	}

}
