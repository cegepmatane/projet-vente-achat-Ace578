package action;

import donnee.MySqlDAO;
import vue.NavigateurDesVues;
import vue.VueGestion;
import vue.VueStatistiques;

public class ControleurStatistiques {
	
	private static ControleurStatistiques instance = null;
	private VueStatistiques vueStatistiques;
	private VueGestion vueGestion;
	private NavigateurDesVues navigateurDesVues = null;
	private MySqlDAO accesseur;
	
	public static ControleurStatistiques getInstance() {
		if(null == instance) instance = new ControleurStatistiques();
		return instance;
	}
	
	private ControleurStatistiques() {
		System.out.println("Initialisation du contrôleur");
		this.accesseur = new MySqlDAO();
	}
	
	public void activerVues(NavigateurDesVues navigateurDesVues) {
		this.navigateurDesVues = navigateurDesVues;
		this.vueStatistiques = navigateurDesVues.getVueStatistiques();
		this.vueGestion = navigateurDesVues.getVueGestion();
		this.navigateurDesVues.naviguerVersVueStatistiques();
	}
	
	public void notifierNaviguerVueGestion() {
		System.out.println("ControleurStatistiques.notifierNaviguerVueGestion");
		navigateurDesVues.naviguerVersVueGestion();
	}
	
	public void notifierNaviguerVueStatistiques() {
		System.out.println("ControleurStatistiques.notifierNaviguerVueStatistiques");
		navigateurDesVues.naviguerVersVueStatistiques();
	}
	
	public MySqlDAO getMySqlDAO() {
		return accesseur;
	}
}
