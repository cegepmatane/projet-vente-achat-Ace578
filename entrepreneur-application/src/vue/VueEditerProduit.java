package vue;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import action.ControleurStatistiques;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import modele.Produit;

public class VueEditerProduit extends Scene {
	
	private ControleurStatistiques controleurStatistiques;
	
	private int idProduit = 0;
	
	private TextField test;

	public VueEditerProduit() {
		super(new BorderPane(), 1050, 800);
		
		BorderPane panneau = (BorderPane) this.getRoot();
		
		test = new TextField();
		
		panneau.setCenter(test);

	}
	
	public void afficherProduit(Produit produit) {
		idProduit = produit.getId();
		test.setText(""+idProduit);
	}

	public void setControleurStatistiques(ControleurStatistiques controleurStatistiques) {
		this.controleurStatistiques = controleurStatistiques;
	}

}