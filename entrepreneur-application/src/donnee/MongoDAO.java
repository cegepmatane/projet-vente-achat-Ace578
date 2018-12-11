package donnee;

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

	public MongoDAO () {
		
		MongoClient mongo = new MongoClient();
		DB vente = mongo.getDB("vente");
		listeProduits = vente.getCollection("produit");
		listeAchats = vente.getCollection("achat");
		listeStickers = vente.getCollection("stickers");

	}
	
	public void ajouterProduit(Produit produit)
	{
		DBObject produitMongo = new BasicDBObject();
		produitMongo.putAll(produit.exporterHash());
		listeProduits.insert(produitMongo);		
	}
	
	
	
	
}
