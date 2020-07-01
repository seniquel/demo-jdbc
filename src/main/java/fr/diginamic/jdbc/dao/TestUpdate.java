package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

import fr.diginamic.jdbc.exceptions.DaoException;

public class TestUpdate {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		int nbLignesModifiees=0;
		try {
			nbLignesModifiees = daoF.update("La Maison de la Peinture", "La Maison des Peintures");
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Nombre de lignes modifiées : "+nbLignesModifiees);
		
	}
}
