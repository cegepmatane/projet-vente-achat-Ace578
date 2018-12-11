package modele;

public class Produit {
	
	String nom, image;
	float prix;
	int idCategorie;
	
	public Produit(String nom, String image, float prix, int idCategorie) {
		this.nom = nom;
		this.image = image;
		this.prix = prix;
		this.idCategorie = idCategorie;
	}

}
