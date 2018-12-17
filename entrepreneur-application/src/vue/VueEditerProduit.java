package vue;

import action.ControleurStatistiques;
import donnee.MySqlDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import modele.Produit;

public class VueEditerProduit extends Scene {
	
	private MySqlDAO accesseur;
	
	private ControleurStatistiques controleurStatistiques;
	
	private BorderPane fenetrePrincipale;
	
	private TextField nom, prix;

	private int idProduit = 0;
	

	public VueEditerProduit() {
		super(new BorderPane(), 1050, 800);
		
		fenetrePrincipale = (BorderPane) this.getRoot();
		
		Label titre = new Label("Editer produit");	
		titre.setFont(Font.font ("Verdana", 30));

		fenetrePrincipale.setTop(titre);
		BorderPane.setAlignment(titre, Pos.CENTER);
		
				
		nom = new TextField();
		prix = new TextField();
		
		GridPane champsProduit = new GridPane();
		champsProduit.add(new Label("Nom : "), 0, 0);
		champsProduit.add(nom, 1, 0);
		champsProduit.add(new Label("Prix : "), 0, 1);
		champsProduit.add(prix, 1, 1);
		
		fenetrePrincipale.setCenter(champsProduit);

	}
	
	public void afficherProduit(Produit produit) {
		idProduit = produit.getId();
		nom.setText(produit.getNom());
		prix.setText(""+produit.getPrix());
	}

	public void setControleurStatistiques(ControleurStatistiques controleurStatistiques) {
		this.controleurStatistiques = controleurStatistiques;
	}

}