package donnee;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.connection.Server;

import modele.Produit;

public class MongoDAO {
	
	protected DBCollection listeProduits = null;
	protected DBCollection listeAchats = null;
	protected DBCollection listeStickers = null;
	protected DBCollection listeCategories = null;

	public MongoDAO () {
		

		MongoClient mongo = new MongoClient("158.69.192.249",27017);
		DB vente = mongo.getDB("vente");
		listeProduits = vente.getCollection("produit");
		listeAchats = vente.getCollection("achat");
		listeStickers = vente.getCollection("stickers");
		listeCategories = vente.getCollection("categorie");

	}
	
	public void ajouterProduit(Produit produit)
	{
		DBObject produitMongo = new BasicDBObject();
		produitMongo.putAll(produit.exporterHash());
		listeProduits.insert(produitMongo);		
	}
	
	public void effacerProduit(int id)
	{
		DBObject critereProduit = new BasicDBObject("id",id);
		this.listeProduits.remove(critereProduit);		
	}
	

	public List<Produit> trouverListeProduitParCategorie(int categorie)
	{
		List<Produit> resultat = new ArrayList<Produit>();
		DBObject critereProduit = new BasicDBObject("id_categorie",categorie);
		DBCursor pointeurProduit = listeProduits.find(critereProduit);
		while (pointeurProduit.hasNext()) {
			Map champsProduitTrouve = pointeurProduit.one().toMap();
			Produit produitTrouve = new Produit(champsProduitTrouve);
			resultat.add(produitTrouve);
			pointeurProduit.next();
		}		
		return resultat;
	}
	
	public Produit trouverProduit(int id)
	{
		DBObject critereProduit = new BasicDBObject("id",id);
		DBCursor pointeurProduit = listeProduits.find(critereProduit);
		Map champsProduitTrouve = pointeurProduit.one().toMap();
		Produit produitTrouve = new Produit(champsProduitTrouve);		
		return produitTrouve;
}
	
	
	
	
	
}
