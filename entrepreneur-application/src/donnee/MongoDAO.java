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

	public MongoDAO () {
		
		MongoClient mongo = new MongoClient();
		DB vente = mongo.getDB("vente");

	}
	
	public void ajouterProduit(Produit produit)
	{
		DBObject produitMongo = new BasicDBObject();
		produitMongo.putAll(produit.exporterHash());
		listeProduits.insert(produitMongo);		
	}
	
	
	
	
}
