package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		List<Fournisseur> listeFournisseurs = daoF.extraire();
		//Affichage de la liste des fournisseurs
		System.out.println("ID\tNOM");
		for(Fournisseur f : listeFournisseurs) {
			System.out.println(f.getId()+"\t"+f.getNom());
		}
	
	}

}
