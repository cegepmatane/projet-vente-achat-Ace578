package modele;

public class StatistiqueMois {
	
	private String mois, meilleurProduit;
	private float moyenne, maximum;
	
	public StatistiqueMois(String mois, float moyenne, float maximum, String meilleurProduit) {
		this.mois = mois;
		this.meilleurProduit = meilleurProduit;
		this.maximum = maximum;
		this.moyenne = moyenne;
	}

	public String getMois() {
		return mois;
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
