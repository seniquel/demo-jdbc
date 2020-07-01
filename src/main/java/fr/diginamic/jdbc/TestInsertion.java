package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestInsertion {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Lecture du fichier de propriétés
        ResourceBundle database = ResourceBundle.getBundle("clevercloud-database");
		
        // Enregistrement du pilote
		Class.forName(database.getString("database.driver"));

		// Création de la connexion
		String url = database.getString("database.url");
		String utilisateur = database.getString("database.user");
		String motDePasse = database.getString("database.pass");	
		// on utilise le try-with-resources pour fermer automatiquement la connexion à la fin du try
		try(Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)){
			connexion.setAutoCommit(false);
			try(Statement statement = connexion.createStatement();  // Création du statement
				PreparedStatement ps = connexion.prepareStatement("insert into FOURNISSEUR(ID,NOM) values (4,'La Maison de la Peinture')")){
				// Exécution de la requête
				ps.executeUpdate();
				connexion.commit();
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				connexion.rollback();
			}
			//On teste si la nouvelle valeur a bien été ajouté dans la table
			try(Statement statement = connexion.createStatement();
				ResultSet resultSet = statement.executeQuery("select NOM from FOURNISSEUR where NOM='La Maison de la Peinture'")){ 
				resultSet.next();
				if(resultSet.getString("NOM").equals("La Maison de la Peinture")) {
					System.out.println("Maison de la peinture est bien dans la table");
				}
				else {
					System.out.println("Maison de la peinture n'est pas dans la table");
				}
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
