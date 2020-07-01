package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDelete {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		boolean supprime = daoF.delete(new Fournisseur(4,"La Maison des Peintures"));
		System.out.println((supprime)?"Ligne supprimée":"Aucune ligne supprimée");

	}

}
