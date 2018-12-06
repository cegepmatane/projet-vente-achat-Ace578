package modele;

public class StatistiqueProduit {

	private String produit, meilleurMois;
	private float moyenne, maximum;
	
	public StatistiqueProduit(String produit, float moyenne, float maximum, String meilleurMois) {
		this.produit = produit;
		this.meilleurMois = meilleurMois;
		this.moyenne = moyenne;
		this.maximum = maximum;
	}

	public String getProduit() {
		return produit;
	}

	public String getMeilleurMois() {
		return meilleurMois;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public float getMaximum() {
		return maximum;
	}
}
