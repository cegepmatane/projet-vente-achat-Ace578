package action;

import java.io.FileNotFoundException;

import donnee.MongoDAO;
import donnee.MySqlDAO;
import modele.Produit;
import vue.NavigateurDesVues;
import vue.VueEditerProduit;
import vue.VueGestion;
import vue.VueStatistiques;

public class ControleurStatistiques {
	
	private static ControleurStatistiques instance = null;
	private VueStatistiques vueStatistiques;
	private VueGestion vueGestion;
	private VueEditerProduit vueEditerProduit;
	private NavigateurDesVues navigateurDesVues = null;
	//private MySqlDAO accesseur;
	private MongoDAO accesseur;
	
	public static ControleurStatistiques getInstance() {
		if(null == instance) instance = new ControleurStatistiques();
		return instance;
	}
	
	private ControleurStatistiques() {
		System.out.println("Initialisation du contrôleur");
		//this.accesseur = new MySqlDAO();
		this.accesseur = new MongoDAO();
	}
	
	public void activerVues(NavigateurDesVues navigateurDesVues) {
		this.navigateurDesVues = navigateurDesVues;
		this.vueStatistiques = navigateurDesVues.getVueStatistiques();
		this.vueGestion = navigateurDesVues.getVueGestion();
		this.vueEditerProduit = navigateurDesVues.getVueEditerProduit();
		//this.navigateurDesVues.naviguerVersVueStatistiques();
		//Tests pour vue Gestion
		this.navigateurDesVues.naviguerVersVueGestion();
	}
	
	public void notifierNaviguerVueGestion() {
		System.out.println("ControleurStatistiques.notifierNaviguerVueGestion");
		navigateurDesVues.naviguerVersVueGestion();
	}
	
	public void notifierNaviguerVueStatistiques() {
		System.out.println("ControleurStatistiques.notifierNaviguerVueStatistiques");
		navigateurDesVues.naviguerVersVueStatistiques();
	}
	
	public /*MySqlDAO*/ MongoDAO getMySqlDAO() {
		return accesseur;
	}

	public void notifierNaviguerVueEditerProduit(int idProduit) {
		System.out.println("ControleurStatistiques.notifierNaviguerEditerProduit");
		vueEditerProduit.afficherProduit(accesseur.recupererProduit(idProduit));
		navigateurDesVues.naviguerVersVueEditerProduit();
	}
	
	public void notifierModifierProduit() {
		System.out.println("ControleurStatistiques.notifierModifierProduit");
		Produit produit = navigateurDesVues.getVueEditerProduit().demanderProduit();
		accesseur.modifierProduit(produit);
		try {
			vueGestion.afficherListeProduits(produit.getIdCategorie());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		navigateurDesVues.naviguerVersVueGestion();
	}
}