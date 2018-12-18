package donnee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Produit;
import modele.StatistiqueCategorie;
import modele.StatistiqueMois;
import modele.StatistiqueProduit;
import modele.StatistiqueRegion;
import redis.clients.jedis.Jedis;

public class MySqlDAO {
	
	private static String BASEDEDONNEES_DRIVER = "org.mariadb.jdbc.Driver";
	private static String BASEDEDONNEES_URL = "jdbc:mariadb://158.69.192.249:3306/vente";
	private static String BASEDEDONNEES_USAGER = "vendeur";
	private static String BASEDEDONNEES_MOTDEPASSE = "password";
	
	private Jedis cache;
	private Gson gson;
	private boolean redisRecent = false;
	private Connection connexion = null;
	private Statement declaration = null;
	
	public MySqlDAO() {
		cache = new Jedis("158.69.192.249", 6379);
		gson = new Gson();
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
			connexion = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			declaration = connexion.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!redisRecent) {
			redisRecent = tempsRedisInferieurAUneHeure();
		}
	}
	
	public String recupererNombreCategories() {
		String resultat = "";
		if(redisRecent) {
			resultat = cache.get("nombreCategories");
		} else {
			try {
				String REQUETE_NOMBRE_CATEGORIES = "SELECT COUNT(*) FROM categorie";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_NOMBRE_CATEGORIES);
				while(resultatRequete.next()) {
					resultat = resultatRequete.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("nombreCategories", resultat);
		}
		return resultat;
	}
	
	public String recupererNombreProduits() {
		String resultat = "";
		if(redisRecent) {
			resultat = cache.get("nombreProduits");
		} else {
			try {
				String REQUETE_NOMBRE_PRODUITS = "SELECT COUNT(*) FROM produit";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_NOMBRE_PRODUITS);
				while(resultatRequete.next()) {
					resultat = resultatRequete.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("nombreProduits", resultat);
		}
		return resultat;
	}
	
	public float recupererRecetteTotal(int annee) {
		float resultat = 0.0f;
		if(redisRecent) {
			resultat = Float.parseFloat(cache.get("recetteTotale"));
		} else {
			try {
				String REQUETE_RECETTE_TOTALE = "SELECT SUM(prix_total) FROM achat_" + annee + "";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_RECETTE_TOTALE);
				while(resultatRequete.next()) {
					resultat = resultatRequete.getFloat(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("recetteTotale", ""+resultat);
		}
		return resultat;
	}
	

	public ObservableList<StatistiqueMois> recupererStatistiquesMoisParAnnee(int annee) {
		List<StatistiqueMois> resultat = new ArrayList<StatistiqueMois>();
		Type listeStatistiquesMois = new TypeToken<ArrayList<StatistiqueMois>>(){}.getType();
		if(redisRecent) {
			resultat = gson.fromJson(cache.get("statistiquesMoisParAnnee"), listeStatistiquesMois);
		} else {
			try {
				String REQUETE_STATISTIQUES_MOIS = "SELECT MONTH(date) as mois, MAX(prix_total) as maximum, AVG(prix_total) as moyenne, produit as meilleur FROM achat_" + annee + " GROUP BY MONTH(date)";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_STATISTIQUES_MOIS);
				while(resultatRequete.next()) {
					String mois = resultatRequete.getString("mois");
					float max = resultatRequete.getFloat("maximum");
					float moyenne = resultatRequete.getFloat("moyenne");
					String REQUETE_MEILLEURE_PRODUIT = "SELECT meilleur FROM (SELECT COUNT(produit) as max, produit as meilleur FROM achat_" + annee + " WHERE MONTH(date) = " + mois + " GROUP BY produit) as meill ORDER BY max DESC limit 1";
					ResultSet resultatRe = declaration.executeQuery(REQUETE_MEILLEURE_PRODUIT);
					while(resultatRe.next()) {
						int meilleur = resultatRe.getInt("meilleur");
						String REQUETE_NOM_MEILLEUR = "SELECT nom FROM produit where id = " + meilleur;
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
			cache.set("statistiquesMoisParAnnee", gson.toJson(resultat));
		}
		return FXCollections.observableArrayList(resultat);
	}

	public ObservableList<StatistiqueCategorie> recupererStatistiquesCategoriesParAnnee(int annee) {
		List<StatistiqueCategorie> resultat = new ArrayList<StatistiqueCategorie>();
		Type listeStatistiquesCategorie = new TypeToken<ArrayList<StatistiqueCategorie>>(){}.getType();
		if(redisRecent) {
			resultat = gson.fromJson(cache.get("statistiquesCategoriesParAnnee"), listeStatistiquesCategorie);
		} else {
			try {
				String REQUETE_STATISTIQUES_CATEGORIE = "SELECT categorie, AVG(prix_total) as moyenne, MAX(prix_total) as maximum, produit FROM achat_" + annee + " GROUP BY categorie;";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_STATISTIQUES_CATEGORIE);
				while(resultatRequete.next()) {
					float max = resultatRequete.getFloat("maximum");
					float moyenne = resultatRequete.getFloat("moyenne");
					int categorie = resultatRequete.getInt("categorie");
					String REQUETE_NOM_CATEGORIE = "SELECT nom FROM categorie where id = " + categorie;
					ResultSet cate = declaration.executeQuery(REQUETE_NOM_CATEGORIE);
					while(cate.next()) {
						String nomCate = cate.getString("nom");					
						String REQUETE_MEILLEURE_PRODUIT = "SELECT meilleur FROM (SELECT COUNT(produit) as max, produit as meilleur FROM achat achat_" + annee + "  WHERE categorie = " + categorie + " GROUP BY produit) as meill ORDER BY max DESC limit 1";
						ResultSet resultatRe = declaration.executeQuery(REQUETE_MEILLEURE_PRODUIT);
						while(resultatRe.next()) {
							int meilleur = resultatRe.getInt("meilleur");
							String REQUETE_NOM_MEILLEUR = "SELECT nom FROM produit where id = " + meilleur;
							ResultSet meill = declaration.executeQuery(REQUETE_NOM_MEILLEUR);
							while(meill.next()) {
								String nomMeill = meill.getString("nom");
								StatistiqueCategorie statCategorie = new StatistiqueCategorie(nomCate, moyenne, max, nomMeill);
								resultat.add(statCategorie);
							}					
						}			
					}	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("statistiquesCategoriesParAnnee", gson.toJson(resultat));
		}
		return FXCollections.observableArrayList(resultat);
	}

	public ObservableList<StatistiqueRegion> recupererStatistiquesRegionParAnnee(int annee) {
		List<StatistiqueRegion> resultat = new ArrayList<StatistiqueRegion>();
		Type listeStatistiquesRegion = new TypeToken<ArrayList<StatistiqueRegion>>(){}.getType();
		if(redisRecent) {
			resultat = gson.fromJson(cache.get("statistiquesRegionParAnnee"), listeStatistiquesRegion);
		} else {
			try {
				String REQUETE_STATISTIQUES_REGION = "SELECT COUNT(produit) as nombreAcheteurs, region FROM achat_" + annee + " GROUP BY region";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_STATISTIQUES_REGION);
				while(resultatRequete.next()) {
					int nombreAcheteurs = resultatRequete.getInt("nombreAcheteurs");
					String region = resultatRequete.getString("region");
					StatistiqueRegion statRegion = new StatistiqueRegion(region, nombreAcheteurs);
					resultat.add(statRegion);
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("statistiquesRegionParAnnee", gson.toJson(resultat));
		}
		return FXCollections.observableArrayList(resultat);
	}

	public ObservableList<StatistiqueProduit> recupererStatistiquesProduitsParAnnee(int annee) {
		List<StatistiqueProduit> resultat = new ArrayList<StatistiqueProduit>();
		Type listeStatistiquesProduits = new TypeToken<ArrayList<StatistiqueProduit>>(){}.getType();
		if(redisRecent) {
			resultat = gson.fromJson(cache.get("statistiquesProduitsParAnnee"), listeStatistiquesProduits);
		} else {
			try {
				String REQUETE_STATISTIQUES_PRODUIT = "SELECT COUNT(produit) as nb, AVG(prix_total) as moyenne, MAX(prix_total) as max,  produit, MONTH(date) as mois FROM achat_" + annee + " GROUP by produit ORDER BY nb DESC LIMIT 5";
				ResultSet resultatRequete = declaration.executeQuery(REQUETE_STATISTIQUES_PRODUIT);
				while(resultatRequete.next()) {
					int meilleur = resultatRequete.getInt("produit");
					String mois = resultatRequete.getString("mois");
					float moyenne = resultatRequete.getFloat("moyenne");
					float max = resultatRequete.getFloat("max");
					String REQUETE_MEILLEURE_PRODUIT = "SELECT nom FROM produit where id = " + meilleur;
					ResultSet resultatRe = declaration.executeQuery(REQUETE_MEILLEURE_PRODUIT);
					while(resultatRe.next()) {
						String nom = resultatRe.getString("nom");
						StatistiqueProduit statProduit = new StatistiqueProduit(nom, moyenne, max,mois);
						resultat.add(statProduit);					
					}			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cache.set("statistiquesProduitsParAnnee", gson.toJson(resultat));
		}		
		return FXCollections.observableArrayList(resultat);
	}
	
	public void ajouterProduit(Produit produit) {
		try {
			String SQL_AJOUTER_PRODUIT = "INSERT into produit(nom, image, prix, id_categorie) VALUES (?,?,?,?)";
			PreparedStatement requeteAjouterProduit = connexion.prepareStatement(SQL_AJOUTER_PRODUIT);
			requeteAjouterProduit.setString(1, produit.getNom());
			requeteAjouterProduit.setString(2, produit.getImage());
			requeteAjouterProduit.setFloat(3, produit.getPrix());
			requeteAjouterProduit.setInt(4, produit.getIdCategorie());
			requeteAjouterProduit.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierProduit(Produit produit) {		
		try {
			String SQL_MODIFIER_PRODUIT = "UPDATE produit SET nom = ?, image = ?, prix = ?, id_categorie = ? WHERE id = ?";
			PreparedStatement requeteModifierProduit = connexion.prepareStatement(SQL_MODIFIER_PRODUIT);
			requeteModifierProduit.setString(1, produit.getNom());
			requeteModifierProduit.setString(2, produit.getImage());
			requeteModifierProduit.setFloat(3, produit.getPrix());
			requeteModifierProduit.setInt(4, produit.getIdCategorie());
			requeteModifierProduit.setInt(5, produit.getId());	
			requeteModifierProduit.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerProduit(Produit produit) {
		String SQL_SUPPRIMER_PRODUIT = "DELETE FROM livre WHERE id = "+produit.getId()+" ";
		try {
			PreparedStatement requeteSupprimerProduit = connexion.prepareStatement(SQL_SUPPRIMER_PRODUIT);
			requeteSupprimerProduit.execute();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Produit> recupererProduitsParCategorie(int categorie) {
		List<Produit> resultat = new ArrayList<Produit>();
		try {
			String REQUETE_CATEGORIE_PRODUITS = "SELECT * FROM produit WHERE id_categorie = " +categorie;
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_CATEGORIE_PRODUITS);
			while(resultatRequete.next()) {
				int id = resultatRequete.getInt("id");
				String nom = resultatRequete.getString("nom");
				String image = resultatRequete.getString("image");
				float prix = resultatRequete.getFloat("prix");
				int idCategorie = resultatRequete.getInt("id_categorie");
				Produit produit = new Produit(id, nom, image, prix, idCategorie);
				resultat.add(produit);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(resultat);
	}

	public Collection<String> recupererCategories() {
		Collection<String> categories = new ArrayList();
		try {
			String REQUETE_CATEGORIES = "SELECT nom FROM categorie";
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_CATEGORIES);
			while (resultatRequete.next()) {
				String nom = resultatRequete.getString("nom");
				categories.add(nom);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
		
	}
	
	public int trouverIdCategorie(String categorie) {
		int id = 0;
		try {
			String REQUETE_ID_CATEGORIE = "SELECT id FROM categorie WHERE nom = '" + categorie + "';";
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_ID_CATEGORIE);
			while (resultatRequete.next()) {
				id = resultatRequete.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Produit recupererProduit(int idProduit) {		
		try {
			String REQUETE_PRODUIT = "SELECT * FROM produit WHERE id = " + idProduit;
			ResultSet resultatRequete = declaration.executeQuery(REQUETE_PRODUIT);
			while(resultatRequete.next()) {
				int id = resultatRequete.getInt("id");
				String nom = resultatRequete.getString("nom");
				String image = resultatRequete.getString("image");
				float prix = resultatRequete.getFloat("prix");
				int idCategorie = resultatRequete.getInt("id_categorie");
				return new Produit(id, nom, image, prix, idCategorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean tempsRedisInferieurAUneHeure() {
		Long tempRedis = Long.parseLong(cache.get("timestamp"));
		System.out.println(System.currentTimeMillis() - tempRedis);
		if(System.currentTimeMillis() - tempRedis < 3600000) {
			return true;
		}
		return false;
	}
}