package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Lecture du fichier de propriétés
        ResourceBundle database = ResourceBundle.getBundle("clevercloud-database");
		
        // Enregistrement du pilote
		Class.forName(database.getString("database.driver"));
		
		//Création de la liste de fournisseurs vide
		List<Fournisseur> listeFournisseurs = new ArrayList<>(); 

		// Création de la connexion
		String url = database.getString("database.url");
		String utilisateur = database.getString("database.user");
		String motDePasse = database.getString("database.pass");	
		try(Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse)){
			try(Statement statement = connexion.createStatement(); // Création du statement
				//Exécution de la requête
				ResultSet resultSet = statement.executeQuery("select * from FOURNISSEUR")){
				// ajout des résultats dans la liste des fournisseurs
				while(resultSet.next()) {
					int id = resultSet.getInt("ID");
					String nom = resultSet.getString("NOM");
					listeFournisseurs.add(new Fournisseur(id,nom));
				}
			}
		}
		//Affichage de la liste des fournisseurs
		System.out.println("ID\tNOM");
		for(Fournisseur f : listeFournisseurs) {
			System.out.println(f.getId()+"\t"+f.getNom());
		}
	}

}
