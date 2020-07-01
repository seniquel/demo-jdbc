package fr.diginamic.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestConnexionJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Lecture du fichier de propriétés
        ResourceBundle database = ResourceBundle.getBundle("clevercloud-database");
		
        // Enregistrement du pilote
		Class.forName(database.getString("database.driver"));
		
		// Création de la connexion
		String url = database.getString("database.url");
		String utilisateur = database.getString("database.user");
		String motDePasse = database.getString("database.pass");
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println(connexion);
			
		} finally {
			if(connexion != null) {
				connexion.close();
			}
		}
		
	}

}