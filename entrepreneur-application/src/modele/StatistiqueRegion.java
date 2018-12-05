package modele;

public class StatistiqueRegion {
	
	private String region;
	private int nombreAcheteurs;
	
	public StatistiqueRegion(String region, int nombreAcheteurs) {
		this.region = region;
		this.nombreAcheteurs = nombreAcheteurs;
	}

	public String getRegion() {
		return region;
	}

	public int getNombreAcheteurs() {
		return nombreAcheteurs;
	}
}
