package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestUpdate {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Lecture du fichier de propri�t�s
        ResourceBundle database = ResourceBundle.getBundle("clevercloud-database");
		
        // Enregistrement du pilote
		Class.forName(database.getString("database.driver"));

		// Cr�ation de la connexion
		String url = database.getString("database.url");
		String utilisateur = database.getString("database.user");
		String motDePasse = database.getString("database.pass");	
		try(Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)){
			connexion.setAutoCommit(false);
			try(Statement statement = connexion.createStatement(); // Cr�ation du statement
				PreparedStatement ps = connexion.prepareStatement("update FOURNISSEUR set NOM='La Maison des Peintures' where NOM='La Maison de la Peinture'")){
					// Ex�cution de la requ�te
					ps.executeUpdate();
					connexion.commit();
				}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				connexion.rollback();
			}
		}
	}
}
