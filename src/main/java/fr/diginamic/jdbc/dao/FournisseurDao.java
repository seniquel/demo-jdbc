package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public interface FournisseurDao {
	List<Fournisseur> extraire() throws ClassNotFoundException, SQLException;
	void insert(Fournisseur fournisseur) throws ClassNotFoundException, SQLException;
	int update(String ancienNom, String nouveauNom) throws ClassNotFoundException, SQLException;
	boolean delete(Fournisseur fournisseur) throws ClassNotFoundException, SQLException;
}
