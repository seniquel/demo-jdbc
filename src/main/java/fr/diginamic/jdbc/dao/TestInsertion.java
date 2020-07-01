package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestInsertion {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		daoF.insert(new Fournisseur(4,"L''espace création"));
	}

}
