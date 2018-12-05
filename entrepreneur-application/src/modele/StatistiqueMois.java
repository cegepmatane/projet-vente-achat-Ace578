package modele;

public class StatistiqueMois {
	
	private String mois, meilleurProduit;
	private int moyenne, maximum;
	private int meilleur;
	private float max, moy;
	
	public StatistiqueMois(String mois, int moyenne, int maximum, String meilleurProduit) {
		this.mois = mois;
		this.meilleurProduit = meilleurProduit;
		this.moyenne = moyenne;
		this.maximum = maximum;
	}
	
	public StatistiqueMois(String mois, float moyenne, float maximum, int meilleurProduit) {
		this.mois = mois;
		this.meilleur = meilleurProduit;
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
	
	
	public float getMoyenneFloat() {
		return moy;
	}
	
	public float getMaximumFloat() {
		return max;
	}
	
	public int getMeilleur() {
		return meilleur;
	}
}
