package vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import action.ControleurStatistiques;
import donnee.MySqlDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import modele.Produit;

public class VueGestion extends Scene {
	
	private MySqlDAO accesseur;
	
	private ControleurStatistiques controleurStatistiques;

	private BorderPane fenetrePrincipale;
	
	private GridPane grilleProduits;

	
	public VueGestion() {
		super(new BorderPane(), 1050, 800);
		
		fenetrePrincipale = (BorderPane) this.getRoot();
		fenetrePrincipale.getChildren().clear();

		this.accesseur = new MySqlDAO();
						
		Label titre = new Label("Gestion");	
		titre.setFont(Font.font ("Verdana", 30));

		fenetrePrincipale.setTop(titre);
		BorderPane.setAlignment(titre, Pos.CENTER);
		
		Label labelCategorie = new Label("Catégorie : ");
		
		ArrayList<String> categorie = new ArrayList<>();
		categorie.addAll(accesseur.recupererCategories());
		
		ComboBox<String> choixCategories = new ComboBox<String>();
		choixCategories.getItems().addAll(categorie);
		choixCategories.setValue(categorie.get(0));
		choixCategories.setVisibleRowCount(4);
		
		Button changerCategorie = new Button("Valider");
		changerCategorie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					afficherListeProduits(accesseur.trouverIdCategorie(choixCategories.getValue().toString()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}				
			}
		});		
		
		GridPane affichageCategorie = new GridPane();	
		affichageCategorie.add(labelCategorie, 0, 0);
		affichageCategorie.add(choixCategories, 1, 0);
		affichageCategorie.add(changerCategorie, 2, 0);
		affichageCategorie.setPadding(new Insets(5));
		GridPane.setMargin(changerCategorie, new Insets(10));
		
		grilleProduits = new GridPane();
		grilleProduits.setPadding(new Insets(10));

		try {
			afficherListeProduits(accesseur.trouverIdCategorie(choixCategories.getValue().toString()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BorderPane elementCentral = new BorderPane();
		elementCentral.setTop(affichageCategorie);
		elementCentral.setCenter(grilleProduits);
		elementCentral.setBottom(new Label());
		
		fenetrePrincipale.setCenter(elementCentral);
	}


	public void afficherListeProduits(int categorie) throws FileNotFoundException {
		grilleProduits.getChildren().clear();
		
		Label labelNom = new Label("Nom");
		Label labelPrix = new Label("Prix");
		Label labelImage = new Label("Image");
		
		grilleProduits.add(labelNom, 0, 0);
		grilleProduits.add(labelPrix, 1, 0);
		grilleProduits.add(labelImage, 2, 0);

		GridPane.setMargin(labelNom, new Insets(10));
		GridPane.setMargin(labelPrix, new Insets(10));
		GridPane.setMargin(labelImage, new Insets(10));
		
		List<Produit> listeProduits = new ArrayList<>();

		listeProduits.addAll(accesseur.recupererProduitsParCategorie(categorie));
		
		Label nom = new Label();
		Label prix = new Label();
		Image image;
		
		int numero = 0;
		
		for (Produit produit : listeProduits) {
			Button actionEditerProduit = new Button("Editer");
			actionEditerProduit.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					controleurStatistiques.notifierNaviguerVueEditerProduit(produit.getId());
				}
			});
			
			numero++;
			
			nom = new Label(produit.getNom());
			prix = new Label(""+produit.getPrix());
			
			/*
			FileInputStream imageStream = new FileInputStream(produit.getImage());
			image = new Image(imageStream);*/
			
			grilleProduits.add(nom, 0, numero);
			grilleProduits.add(prix, 1, numero);
			//grilleProduits.add(new ImageView(image), 2, numero);
			grilleProduits.add(actionEditerProduit, 3, numero);
			
			GridPane.setMargin(nom, new Insets(10));
			GridPane.setMargin(prix, new Insets(10));
			GridPane.setMargin(actionEditerProduit, new Insets(10));
		}
	}


	public void setControleurStatistiques(ControleurStatistiques controleurStatistiques) {
		this.controleurStatistiques = controleurStatistiques;
	}

}
