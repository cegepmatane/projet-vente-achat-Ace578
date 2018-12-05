package modele;

public class StatistiqueProduit {

	private String produit, meilleurMois;
	private int moyenne, maximum;
	
	public StatistiqueProduit(String produit, int moyenne, int maximum, String meilleurMois) {
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

	public int getMoyenne() {
		return moyenne;
	}

	public int getMaximum() {
		return maximum;
	}
}
