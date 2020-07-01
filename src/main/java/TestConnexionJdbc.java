import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
		String url = "jdbc:mariadb://localhost:3306/compta";
		String user = "root";
		String pwd = "";
		Connection connexion = DriverManager.getConnection(url, user, pwd);
		System.out.println(connexion);
		connexion.close();
	}

}
