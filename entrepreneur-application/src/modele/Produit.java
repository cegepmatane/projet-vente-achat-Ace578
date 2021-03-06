package modele;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

public class Produit {
	
	protected int id;
	ObjectId idMongo;
	String nom, image;
	float prix;
	int idCategorie;
	ObjectId idCategorieMongo;
	
	public Produit(String nom, String image, float prix, int idCategorie) {
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorie = idCategorie;
	}
	
	public Produit(String nom, String image, float prix, ObjectId idCategorieMongo) {
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorieMongo = idCategorieMongo;
	}
		
	public ObjectId getIdCategorieMongo() {
		return idCategorieMongo;
	}

	public void setIdCategorieMongo(ObjectId idCategorieMongo) {
		this.idCategorieMongo = idCategorieMongo;
	}

	public Produit(Map<String, Object> champs)
    {
		//System.out.println(champs.toString());
		this.idMongo = (ObjectId) champs.get("_id");
		this.nom = (String) champs.get("nom");
		this.image = (String) champs.get("image");
		this.prix = (float)((double) champs.get("prix"));
        this.idCategorieMongo = (ObjectId) champs.get("id_categorie");
    }
	
	public Produit(int id, String nom, String image, float prix, int idCategorie) {
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorie = idCategorie;
	}
	
	public Produit(ObjectId idMongo, String nom, String image, float prix, ObjectId idCategorieMongo) {
		this.idMongo = idMongo;
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorieMongo = idCategorieMongo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Map<String, Object> exporterHash() {
		Map<String, Object> hash = new HashMap<String, Object>();
		//hash.put("_id", this.idMongo + "");
		hash.put("nom", this.nom);
		hash.put("image", this.image);
		hash.put("prix", this.prix);
		hash.put("id_categorie", this.idCategorieMongo);
		return hash;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", idMongo=" + idMongo + ", nom=" + nom + ", image=" + image + ", prix=" + prix
				+ ", idCategorie=" + idCategorie + ", idCategorieMongo=" + idCategorieMongo + "]";
	}

	public ObjectId getIdMongo() {
		return idMongo;
	}

	public void setIdMongo(ObjectId idMongo) {
		this.idMongo = idMongo;
	}
}
