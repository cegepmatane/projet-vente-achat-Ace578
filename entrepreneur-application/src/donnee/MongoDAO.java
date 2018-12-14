package donnee;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import modele.Produit;

public class MongoDAO {
	
	protected DBCollection listeProduits = null;
	protected DBCollection listeAchats = null;
	protected DBCollection listeStickers = null;
	protected DBCollection listeCategories = null;

	public MongoDAO () {
		
		MongoClient mongo = new MongoClient();
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
	
	
	public Produit trouverProduit(int id)
	{
		DBObject critereProduit = new BasicDBObject("id",id);
		DBCursor pointeurProduit = listeProduits.find(critereProduit);
		Map champsProduitTrouve = pointeurProduit.one().toMap();
		Object prix = champsProduitTrouve.get("prix");
		Produit produitTrouve = new Produit(champsProduitTrouve);
		float prixf = (float) prix;
		produitTrouve.setPrix(prixf);
		return produitTrouve;
	}
	
	
	
}
