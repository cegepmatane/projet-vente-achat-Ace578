package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import modele.StatistiqueCategorie;
import modele.StatistiqueMois;
import modele.StatistiqueProduit;
import modele.StatistiqueRegion;

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

			String REQUETE_STATISTIQUES_MOIS = "SELECT MONTH(date) as mois, MAX(prix_total) as maximum, AVG(prix_total) as moyenne, produit as meilleur FROM achat WHERE YEAR(date)= " +annee+ " GROUP BY MONTH(date)";
			System.out.println(REQUETE_STATISTIQUES_MOIS);
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_STATISTIQUES_MOIS);

			while(resultatRequete.next()) {
				String mois = resultatRequete.getString("mois");
				float max = resultatRequete.getFloat("maximum");
				float moyenne = resultatRequete.getFloat("moyenne");
				String REQUETE_MEILLEURE_PRODUIT = "SELECT meilleur FROM (SELECT COUNT(produit)as max, produit as meilleur FROM achat WHERE MONTH(date) = " +mois+ " AND YEAR(date) = " +annee+ " GROUP BY produit) as meill ORDER BY max DESC limit 1";
				ResultSet resultatRe = declaration.executeQuery(REQUETE_MEILLEURE_PRODUIT);
				while(resultatRe.next()) {
					int meilleur = resultatRe.getInt("meilleur");
					String REQUETE_NOM_MEILLEUR = "SELECT nom FROM produit where id="+meilleur;
					ResultSet meill = declaration.executeQuery(REQUETE_NOM_MEILLEUR);
					while(meill.next()) {
						String nomMeill = meill.getString("nom");
						StatistiqueMois statMois = new StatistiqueMois(mois, moyenne, max, nomMeill);
						resultat.add(statMois);
					}					
				}			
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
	
	public List<StatistiqueCategorie> recupererStatistiquesCategoriesParAnnee(int annee){
		List<StatistiqueCategorie> resultat = new ArrayList<StatistiqueCategorie>();
		
		resultat.add(new StatistiqueCategorie("Maillot", 100, 200, "Maillot Bleu"));
		resultat.add(new StatistiqueCategorie("Chaussure", 80, 420, "Chaussure Rouge"));
		resultat.add(new StatistiqueCategorie("Ballon", 10, 456, "Ballon mousse"));
		
		return resultat;
	}
}