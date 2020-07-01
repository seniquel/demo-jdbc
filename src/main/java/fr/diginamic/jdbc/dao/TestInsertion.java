package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestInsertion {

	public static void main(String[] args) {

		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		try {
			daoF.insert(new Fournisseur(4,"L'espace création"));
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
		}

	}

}
