package modele;

public class StatistiqueCategorie {

	private String categorie, meilleurProduit;
	private float moyenne, maximum;
	
	public StatistiqueCategorie(String categorie, float moyenne, float maximum, String meilleurProduit) {
		this.categorie = categorie;
		this.meilleurProduit = meilleurProduit;
		this.moyenne = moyenne;
		this.maximum = maximum;
	}

	public String getCategorie() {
		return categorie;
	}

	public String getMeilleurProduit() {
		return meilleurProduit;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public float getMaximum() {
		return maximum;
	}
}
