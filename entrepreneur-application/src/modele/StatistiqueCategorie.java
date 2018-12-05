package modele;

public class StatistiqueCategorie {

	private String categorie, meilleurProduit;
	private int moyenne, maximum;
	
	public StatistiqueCategorie(String categorie, int moyenne, int maximum, String meilleurProduit) {
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

	public int getMoyenne() {
		return moyenne;
	}

	public int getMaximum() {
		return maximum;
	}
}
