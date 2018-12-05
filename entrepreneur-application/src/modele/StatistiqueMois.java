package modele;

public class StatistiqueMois {
	
	private String mois, meilleurProduit;
	private int moyenne, maximum;
	private int categorie;
	private float max, moy;
	
	public StatistiqueMois(String mois, int moyenne, int maximum, String meilleurProduit) {
		this.mois = mois;
		this.meilleurProduit = meilleurProduit;
		this.moyenne = moyenne;
		this.maximum = maximum;
	}
	
	public StatistiqueMois(String mois, float moyenne, float maximum, int categorie) {
		this.mois = mois;
		this.categorie = categorie;
		this.max = maximum;
		this.moy = moyenne;
		
	}

	public String getMois() {
		return mois;
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
