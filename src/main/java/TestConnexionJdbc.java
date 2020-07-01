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
		Statement statement = null;
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			System.out.println(connexion);
			statement = connexion.createStatement();
			statement.execute("create table ARTICLE (ID int NOT NULL PRIMARY KEY, REF varchar(13) NOT NULL, DESIGNATION\r\n" + 
					"varchar(255) NOT NULL, PRIX decimal(7,2) NOT NULL, ID_FOU int NOT NULL);\r\n" + 
					"create table FOURNISSEUR (ID int NOT NULL PRIMARY KEY, NOM varchar(25) NOT NULL);\r\n" + 
					"create table BON (ID int NOT NULL PRIMARY KEY, NUMERO int, DATE_CMDE DATETIME DEFAULT\r\n" + 
					"CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, DELAI int, ID_FOU int not null);\r\n" + 
					"create table COMPO (ID int NOT NULL AUTO_INCREMENT PRIMARY KEY, ID_ART int, ID_BON int, QTE\r\n" + 
					"int);");
			
		} finally {
			if(connexion != null) {
				connexion.close();
			}
		}
		
	}

}