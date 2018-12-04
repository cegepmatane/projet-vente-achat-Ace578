package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDAO {
	
	private static String BASEDEDONNEES_DRIVER = "com.mysql.jdbc.Driver";
	private static String BASEDEDONNEES_URL = "jdbc:mysql://158.69.192.249:3306/vente";
	private static String BASEDEDONNEES_USAGER = "vendeur";
	private static String BASEDEDONNEES_MOTDEPASSE = "password";
	
	private Connection connexion;
	private Statement declaration;
	
	public MySqlDAO() {
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
			connexion = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			declaration = connexion.createStatement();
			ResultSet resultat = declaration.executeQuery("SELECT * FROM produit;");
			System.out.println(resultat);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}