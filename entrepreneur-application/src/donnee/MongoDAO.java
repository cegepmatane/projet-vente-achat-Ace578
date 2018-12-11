package donnee;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDAO {
	
	protected DBCollection listeProduits = null;

	public MongoDAO () {
		
		MongoClient mongo = new MongoClient();
		DB bergerie = mongo.getDB("vente");

	}
	
	
	
}
