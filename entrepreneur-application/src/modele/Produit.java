package modele;

import java.util.HashMap;
import java.util.Map;

public class Produit {
	
	protected int id;
	String nom, image;
	float prix;
	int idCategorie;
	
	public Produit(String nom, String image, float prix, int idCategorie) {
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorie = idCategorie;
	}
		
	public Produit(Map<String, String> champs)
	{
		this.nom = champs.get("nom");
		this.image = champs.get("image");
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
	
	public Map<String, String> exporterHash() {
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("nom", this.nom);
		hash.put("image", this.image);
		hash.put("prix", this.prix + "");
		hash.put("id_categorie", this.idCategorie + "");
		return hash;
	}

}
