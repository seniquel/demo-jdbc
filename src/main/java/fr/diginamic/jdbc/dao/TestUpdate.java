package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

public class TestUpdate {

	public static void main(String[] args) {
		
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		int nbLignesModifiees=0;
		try {
			nbLignesModifiees = daoF.update("La Maison de la Peinture", "La Maison des Peintures");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Nombre de lignes modifiées : "+nbLignesModifiees);
		
	}
}
