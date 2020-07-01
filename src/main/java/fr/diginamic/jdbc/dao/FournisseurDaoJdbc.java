package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;
import fr.diginamic.jdbc.exceptions.DaoException;

public class FournisseurDaoJdbc implements FournisseurDao {
	@Override
	public List<Fournisseur> extraire() {
		//Création de la liste de fournisseurs vide
		List<Fournisseur> listeFournisseurs = new ArrayList<>(); 
		try(Connection connexion = connexionCleverCloud()){
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
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return listeFournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {	
		try(Connection connexion = connexionCleverCloud()){
			connexion.setAutoCommit(false);
			try(Statement statement = connexion.createStatement();  // Création du statement
				PreparedStatement ps = connexion.prepareStatement("insert into FOURNISSEUR(ID,NOM) values (?,?)")){
				// Exécution de la requête
				ps.setInt(1, fournisseur.getId());
				ps.setString(2, fournisseur.getNom());
				ps.executeUpdate();
				connexion.commit();
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				connexion.rollback();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		int lignes = 0;
		try(Connection connexion = connexionCleverCloud()){
			connexion.setAutoCommit(false);
			try(Statement statement = connexion.createStatement(); // Création du statement
					PreparedStatement ps = connexion.prepareStatement("update FOURNISSEUR set NOM=? where NOM=?")){
				// Exécution de la requête
				ps.setString(1, nouveauNom);
				ps.setString(2, ancienNom);
				lignes = ps.executeUpdate();
				connexion.commit();
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				connexion.rollback();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return lignes;

	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		boolean supprime = false;
		try(Connection connexion = connexionCleverCloud()){
			connexion.setAutoCommit(false);
			try(Statement statement = connexion.createStatement(); //Création du statement
					PreparedStatement ps = connexion.prepareStatement("delete from FOURNISSEUR where NOM=? and ID=?")){
				// Exécution de la requête
				ps.setString(1, fournisseur.getNom());
				ps.setInt(2, fournisseur.getId());
				int nbLignesSupprimees = ps.executeUpdate();
				connexion.commit();
				// Au moins 1 ligne a bien été supprimée
				supprime = nbLignesSupprimees > 0;
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				connexion.rollback();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return supprime;
	}


	private Connection connexionCleverCloud() {
		// Lecture du fichier de propriétés
		ResourceBundle database = ResourceBundle.getBundle("clevercloud-database");
		// Enregistrement du pilote
		try {
			Class.forName(database.getString("database.driver"));
		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		}
		// Création de la connexion
		String url = database.getString("database.url");
		String utilisateur = database.getString("database.user");
		String motDePasse = database.getString("database.pass");	
		Connection connexion;
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return connexion;	
	}

}
