package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDelete {

	public static void main(String[] args) {

		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		boolean supprime=false;
		try {
			supprime = daoF.delete(new Fournisseur(4,"La Maison des Peintures"));
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
		}
		System.out.println((supprime)?"Ligne supprimée":"Aucune ligne supprimée");

	}

}
