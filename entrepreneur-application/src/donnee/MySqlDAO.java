package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import modele.StatistiqueMois;
import modele.StatistiqueProduit;

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
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_NOMBRE_CATEGORIES);
			while(resultatRequete.next()) {
				resultat = resultatRequete.getString(1);
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
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_NOMBRE_PRODUITS);
			while(resultatRequete.next()) {
				resultat = resultatRequete.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	public List<StatistiqueMois> recupererStatistiquesMoisParAnnee(int annee) {
		List<StatistiqueMois> resultat = new ArrayList<StatistiqueMois>();
		
		try {
			String REQUETE_NOMBRE_PRODUITS = "SELECT MONTH(date) as mois, MAX(prix_total) as maximum, AVG(prix_total) as moyenne, produit as meilleur FROM achat WHERE YEAR(date)= " +annee+ " GROUP BY MONTH(date)";
			System.out.println(REQUETE_NOMBRE_PRODUITS);
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_NOMBRE_PRODUITS);
			while(resultatRequete.next()) {
				String mois = resultatRequete.getString("mois");
				float max = resultatRequete.getFloat("maximum");
				float moyenne = resultatRequete.getFloat("moyenne");
				int meilleur = resultatRequete.getInt("meilleur");
				StatistiqueMois statMois = new StatistiqueMois(mois, moyenne, max, meilleur);
				resultat.add(statMois);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultat;
	}

	public List<StatistiqueProduit> recupererStatistiquesProduitsParAnnee(int annee) {
		List<StatistiqueProduit> resultat = new ArrayList<StatistiqueProduit>();

		resultat.add(new StatistiqueProduit("Maillot Bleu", 100, 200, "Jan"));
		resultat.add(new StatistiqueProduit("Maillot Vert", 80, 420, "Mar"));
		resultat.add(new StatistiqueProduit("Maillot Jaune", 10, 456, "Sep"));
		resultat.add(new StatistiqueProduit("Maillot Rouge", 54, 125, "Nov"));
		
		return resultat;
	}
}