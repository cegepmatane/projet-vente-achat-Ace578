package vue;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VueStatistiques extends Application {

	@Override
	public void start(Stage stade) throws Exception {
		
		Label titre = new Label("Statistiques");	
		titre.setFont(Font.font ("Verdana", 30));
		
		Label sousTitre = new Label("X Catégories - X Produits");
		sousTitre.setFont(Font.font ("Verdana", 15));
		
		GridPane hautApplication = new GridPane();		
		GridPane.setHalignment(titre, HPos.CENTER);
		GridPane.setHalignment(sousTitre, HPos.CENTER);
		hautApplication.add(titre, 1, 0);
		hautApplication.add(sousTitre, 1, 1);
		hautApplication.setAlignment(Pos.CENTER);
				
		Label labelAnnee = new Label("Année : ");
		DatePicker annee = new DatePicker();
		
		GridPane affichageAnnee = new GridPane();		
		affichageAnnee.add(labelAnnee, 0, 0);
		affichageAnnee.add(annee, 1, 0);
		affichageAnnee.setPadding(new Insets(5));
		
		Label titreParMois = new Label("Par Mois");
		
		VBox statistiquesParMois = new VBox();
		for(int i = 0; i<10; i++) {
			//Ajout des statistiques par mois
			statistiquesParMois.getChildren().add(new Label("Exemple"));
		}
		
		ScrollPane affichageStatistiquesParMois = new ScrollPane();
		affichageStatistiquesParMois.setContent(statistiquesParMois);
		affichageStatistiquesParMois.setPrefHeight(200);
		affichageStatistiquesParMois.setPrefWidth(300);

		BorderPane grilleStatistiquesParMois = new BorderPane();
		BorderPane.setAlignment(titreParMois, Pos.CENTER);
		grilleStatistiquesParMois.setTop(titreParMois);
		grilleStatistiquesParMois.setCenter(affichageStatistiquesParMois);
		grilleStatistiquesParMois.setStyle("-fx-border-color: black");
		
		Label titreParProduit = new Label("Par Produit (les 5 meilleurs)");
		
		VBox statistiquesParProduit = new VBox();
		for(int i = 0; i<10; i++) {
			//Ajout des statistiques par produit
			statistiquesParProduit.getChildren().add(new Label("Exemple"));
		}
		
		ScrollPane affichageStatistiquesParProduit = new ScrollPane();
		affichageStatistiquesParProduit.setContent(statistiquesParProduit);
		affichageStatistiquesParProduit.setPrefHeight(200);
		affichageStatistiquesParProduit.setPrefWidth(300);
		
		BorderPane grilleStatistiquesParProduit = new BorderPane();
		BorderPane.setAlignment(titreParProduit, Pos.CENTER);
		grilleStatistiquesParProduit.setTop(titreParProduit);
		grilleStatistiquesParProduit.setCenter(affichageStatistiquesParProduit);
		grilleStatistiquesParProduit.setStyle("-fx-border-color: black");
		
		Label titreParCategorie = new Label("Par Catégorie");
		
		VBox statistiquesParCategorie = new VBox();
		for(int i = 0; i<10; i++) {
			//Ajout des statistiques par catégorie
			statistiquesParCategorie.getChildren().add(new Label("Exemple"));
		}
		
		ScrollPane affichageStatistiquesParCategorie = new ScrollPane();
		affichageStatistiquesParCategorie.setContent(statistiquesParCategorie);
		affichageStatistiquesParCategorie.setPrefHeight(200);
		affichageStatistiquesParCategorie.setPrefWidth(300);
		
		BorderPane grilleStatistiquesParCategorie = new BorderPane();
		BorderPane.setAlignment(titreParCategorie, Pos.CENTER);
		grilleStatistiquesParCategorie.setTop(titreParCategorie);
		grilleStatistiquesParCategorie.setCenter(affichageStatistiquesParCategorie);
		grilleStatistiquesParCategorie.setStyle("-fx-border-color: black");
		
		Label titreParRegion = new Label("Par Région");

		VBox statistiquesParRegion = new VBox();
		for(int i = 0; i<10; i++) {
			//Ajout des statistiques par catégorie
			statistiquesParRegion.getChildren().add(new Label("Exemple"));
		}
		
		ScrollPane affichageStatistiquesParRegion = new ScrollPane();
		affichageStatistiquesParRegion.setContent(statistiquesParRegion);
		affichageStatistiquesParRegion.setPrefHeight(200);
		affichageStatistiquesParRegion.setPrefWidth(300);
		
		BorderPane grilleStatistiquesParRegion = new BorderPane();
		BorderPane.setAlignment(titreParRegion, Pos.CENTER);
		grilleStatistiquesParRegion.setTop(titreParRegion);
		grilleStatistiquesParRegion.setCenter(affichageStatistiquesParRegion);
		grilleStatistiquesParRegion.setStyle("-fx-border-color: black");
		
		GridPane affichageStatistiques = new GridPane();		
		affichageStatistiques.add(grilleStatistiquesParMois, 0, 0);
		affichageStatistiques.add(grilleStatistiquesParProduit, 0, 1);
		affichageStatistiques.add(grilleStatistiquesParCategorie, 1, 0);
		affichageStatistiques.add(grilleStatistiquesParRegion, 1, 1);
		affichageStatistiques.setAlignment(Pos.CENTER);
		GridPane.setMargin(grilleStatistiquesParRegion, new Insets(5));
		GridPane.setMargin(grilleStatistiquesParCategorie, new Insets(5));
		GridPane.setMargin(grilleStatistiquesParProduit, new Insets(5));
		GridPane.setMargin(grilleStatistiquesParMois, new Insets(5));

		affichageStatistiques.setStyle("-fx-border-color: red");
		
		BorderPane elementCentral = new BorderPane();
		elementCentral.setTop(affichageAnnee);
		elementCentral.setCenter(affichageStatistiques);
		
		BorderPane fenetrePrincipale = new BorderPane();
		fenetrePrincipale.setTop(hautApplication);
		fenetrePrincipale.setCenter(elementCentral);
		
		stade.setScene(new Scene(fenetrePrincipale, 600, 500));
		stade.setTitle("Volet Entreprise Vente Achat");
		stade.show();
		
	}
}