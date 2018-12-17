package vue;

import org.bson.types.ObjectId;

import action.ControleurStatistiques;
import donnee.MongoDAO;
import donnee.MySqlDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import modele.Produit;

public class VueEditerProduit extends Scene {
	
	//private MySqlDAO accesseur;
	
	private MongoDAO accesseur;
	
	private ControleurStatistiques controleurStatistiques;
	
	private BorderPane fenetrePrincipale;
	
	private TextField nom, prix;
	
	//private int idProduit;

	private ObjectId idProduit;
	
	private ObjectId idMongo;
	

	public VueEditerProduit() {
		super(new BorderPane(), 1050, 800);
		
		fenetrePrincipale = (BorderPane) this.getRoot();
		
		//this.accesseur = new MySqlDAO();
		
		this.accesseur = new MongoDAO();
		
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
		
		Button actionValider = new Button("Valider");
		actionValider.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controleurStatistiques.notifierModifierProduit();
			}
			
		});
		
		champsProduit.add(actionValider, 0, 2);
		
		fenetrePrincipale.setCenter(champsProduit);
	}
	
	
	public void afficherProduit(Produit produit) {
		//idProduit = produit.getId();
		idMongo = produit.getIdMongo();
		nom.setText(produit.getNom());
		prix.setText(""+produit.getPrix());
	}

	public void setControleurStatistiques(ControleurStatistiques controleurStatistiques) {
		this.controleurStatistiques = controleurStatistiques;
	}

	public Produit demanderProduit() {
		Produit ancienProduit = accesseur.recupererProduit(/*idProduit*/idMongo);
		//Produit nouveauProduit = new Produit(idProduit, nom.getText(), ancienProduit.getImage(), Float.parseFloat(prix.getText()), ancienProduit.getIdCategorie());
		Produit nouveauProduit = new Produit(idMongo, nom.getText(), ancienProduit.getImage(), Float.parseFloat(prix.getText()), ancienProduit.getIdCategorieMongo());
		return nouveauProduit;
	}

}