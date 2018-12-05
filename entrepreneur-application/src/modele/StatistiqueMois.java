package modele;

public class StatistiqueMois {
	
	private String mois, meilleurProduit;
	private int moyenne, maximum;
	
	public StatistiqueMois(String mois, int moyenne, int maximum, String meilleurProduit) {
		this.mois = mois;
		this.meilleurProduit = meilleurProduit;
		this.moyenne = moyenne;
		this.maximum = maximum;
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
