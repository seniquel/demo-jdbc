package fr.diginamic.jdbc.dao;

import java.sql.SQLException;

public class TestUpdate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		FournisseurDaoJdbc daoF = new FournisseurDaoJdbc();
		int nbLignesModifiees = daoF.update("La Maison de la Peinture", "La Maison des Peintures");
		System.out.println("Nombre de lignes modifiées : "+nbLignesModifiees);
		
	}
}
