package donnee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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
	
	public void ajouterProduit(Produit produit) {
		DBObject produitMongo = new BasicDBObject();
		produitMongo.putAll(produit.exporterHash());
		listeProduits.insert(produitMongo);		
	}
	
	public void effacerProduit(int id) {
		DBObject critereProduit = new BasicDBObject("id",id);
		this.listeProduits.remove(critereProduit);		
	}
	

	public List<Produit> recupererProduitsParCategorie(ObjectId categorie) {
        List<Produit> resultat = new ArrayList<Produit>();
        DBObject critereProduit = new BasicDBObject("id_categorie", categorie);
        DBCursor pointeurProduit = listeProduits.find(critereProduit);
        System.out.println(pointeurProduit);
        while (pointeurProduit.hasNext()) {    
            DBObject produitMongo = pointeurProduit.next();
            Map champsProduitTrouve = produitMongo.toMap();            
            Produit produitTrouve = new Produit(champsProduitTrouve);            
            resultat.add(produitTrouve);
        }        
        return resultat;
    }

	public ObjectId trouverIdCategorie(String nom) {
		ObjectId idCategorie;
		DBObject nomCategorie = new BasicDBObject("nom", nom);
		DBCursor pointeurCategorie = listeCategories.find(nomCategorie);
		while (pointeurCategorie.hasNext()) {
			idCategorie = (ObjectId) pointeurCategorie.next().get("_id");
			System.out.println("Id catégorie : " + idCategorie);
			return idCategorie;		
		}		
		
		return null;
	}

	public ArrayList<String> recupererCategories() {
		ArrayList<String> resultat = new ArrayList<String>();
		DBCursor pointeurCategories = listeCategories.find();
		while (pointeurCategories.hasNext()) {
			resultat.add((String) pointeurCategories.next().get("nom"));
		}		
		return resultat;
	}

	public Produit recupererProduit(ObjectId id) {
		DBObject critereProduit = new BasicDBObject("_id", id);
		DBCursor pointeurProduit = listeProduits.find(critereProduit);
		Map champsProduitTrouve = pointeurProduit.one().toMap();
		Produit produitTrouve = new Produit(champsProduitTrouve);		
		return produitTrouve;
	}

	public void modifierProduit(Produit produit) {

	}	
}
