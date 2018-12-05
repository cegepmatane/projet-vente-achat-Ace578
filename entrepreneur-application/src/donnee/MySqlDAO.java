package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class MySqlDAO {
	
	private static String BASEDEDONNEES_DRIVER = "org.mariadb.jdbc.Driver";
	private static String BASEDEDONNEES_URL = "jdbc:mariadb://158.69.192.249:3306/vente";
	private static String BASEDEDONNEES_USAGER = "vendeur";
	private static String BASEDEDONNEES_MOTDEPASSE = "password";
	
	private Connection connexion = null;
	private Statement declaration = null;
	
	public MySqlDAO() {
		try {
			System.out.println("Connexion MariaDB");
			Class.forName(BASEDEDONNEES_DRIVER);
			connexion = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			declaration = connexion.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String recupererNombreCategories() {
		String resultat = "";
		try {
			String REQUETE_NOMBRE_CATEGORIES = "SELECT COUNT(*) FROM categorie";
			System.out.println(REQUETE_NOMBRE_CATEGORIES);
			ResultSet resulatRequete = declaration.executeQuery(REQUETE_NOMBRE_CATEGORIES);
			while(resulatRequete.next()) {
				resultat = resulatRequete.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}
	
	public String recupererNombreProduits() {
		String resultat = "";
		try {
			String REQUETE_NOMBRE_PRODUITS = "SELECT COUNT(*) FROM produit";
			System.out.println(REQUETE_NOMBRE_PRODUITS);
			ResultSet resulatRequete = declaration.executeQuery(REQUETE_NOMBRE_PRODUITS);
			while(resulatRequete.next()) {
				resultat = resulatRequete.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}
}