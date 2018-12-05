package vue;

import java.util.Calendar;

import donnee.MySqlDAO;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VueStatistiques extends Application {
	
	private MySqlDAO accesseur;

	@Override
	public void start(Stage stade) throws Exception {
		
		this.accesseur = new MySqlDAO();
		
		initialiserDonneesParAnnee(Calendar.getInstance().get(Calendar.YEAR));
				
		Label titre = new Label("Statistiques");	
		titre.setFont(Font.font ("Verdana", 30));
		
		Label sousTitre = new Label(accesseur.recupererNombreCategories() + " Catégories - " + accesseur.recupererNombreProduits() + " Produits");
		sousTitre.setFont(Font.font ("Verdana", 15));
		
		GridPane hautApplication = new GridPane();		
		GridPane.setHalignment(titre, HPos.CENTER);
		GridPane.setHalignment(sousTitre, HPos.CENTER);
		hautApplication.add(titre, 1, 0);
		hautApplication.add(sousTitre, 1, 1);
		hautApplication.setAlignment(Pos.CENTER);
				
		Label labelAnnee = new Label("Année : ");
		DatePicker annee = new DatePicker();
		Button changerDate = new Button("Valider");
		
		GridPane affichageAnnee = new GridPane();		
		affichageAnnee.add(labelAnnee, 0, 0);
		affichageAnnee.add(annee, 1, 0);
		affichageAnnee.add(changerDate, 2, 0);
		affichageAnnee.setPadding(new Insets(5));
		GridPane.setMargin(changerDate, new Insets(10));
		
		Label titreParMois = new Label("Par Mois");
		
		VBox statistiquesParMois = new VBox();
		for(int i = 0; i<10; i++) {
			GridPane donneesStatistiquesParMois = new GridPane();
			donneesStatistiquesParMois.add(new Label("moy"), 0, 0);
			donneesStatistiquesParMois.add(new Label("max"), 1, 0);
			donneesStatistiquesParMois.add(new Label("meilleur produit"), 2, 0);
			donneesStatistiquesParMois.add(new Label("840"), 0, 1);
			donneesStatistiquesParMois.add(new Label("10"), 1, 1);
			donneesStatistiquesParMois.add(new Label("Gilet Jaune"), 2, 1);
			donneesStatistiquesParMois.setAlignment(Pos.CENTER);
			donneesStatistiquesParMois.setHgap(10);
			
			Label date = new Label("01/08/2018");
			
			BorderPane caseStatistiqueParMois = new BorderPane();
			BorderPane.setAlignment(date, Pos.CENTER);
			BorderPane.setMargin(date, new Insets(10));
			caseStatistiqueParMois.setLeft(date);
			caseStatistiqueParMois.setCenter(donneesStatistiquesParMois);
			caseStatistiqueParMois.setPrefWidth(400);
			caseStatistiqueParMois.setPrefHeight(50);
			
			statistiquesParMois.getChildren().add(caseStatistiqueParMois);
		}
		
		ScrollPane affichageStatistiquesParMois = new ScrollPane();
		affichageStatistiquesParMois.setContent(statistiquesParMois);
		affichageStatistiquesParMois.setPrefHeight(200);
		affichageStatistiquesParMois.setPrefWidth(430);

		BorderPane grilleStatistiquesParMois = new BorderPane();
		BorderPane.setAlignment(titreParMois, Pos.CENTER);
		grilleStatistiquesParMois.setTop(titreParMois);
		grilleStatistiquesParMois.setCenter(affichageStatistiquesParMois);
		
		Label titreParProduit = new Label("Par Produit (les 5 meilleurs)");
		
		VBox statistiquesParProduit = new VBox();
		for(int i = 0; i<10; i++) {
			GridPane donneesStatistiquesParProduit = new GridPane();
			donneesStatistiquesParProduit.add(new Label("moy"), 0, 0);
			donneesStatistiquesParProduit.add(new Label("max"), 1, 0);
			donneesStatistiquesParProduit.add(new Label("meilleur mois"), 2, 0);
			donneesStatistiquesParProduit.add(new Label("840"), 0, 1);
			donneesStatistiquesParProduit.add(new Label("10"), 1, 1);
			donneesStatistiquesParProduit.add(new Label("05/10/2018"), 2, 1);
			donneesStatistiquesParProduit.setAlignment(Pos.CENTER);
			donneesStatistiquesParProduit.setHgap(10);
			
			Label produit = new Label("Gilet Jaune");
			
			BorderPane caseStatistiqueParProduit = new BorderPane();
			BorderPane.setAlignment(produit, Pos.CENTER);
			BorderPane.setMargin(produit, new Insets(10));
			caseStatistiqueParProduit.setLeft(produit);
			caseStatistiqueParProduit.setCenter(donneesStatistiquesParProduit);
			caseStatistiqueParProduit.setPrefWidth(400);
			caseStatistiqueParProduit.setPrefHeight(50);
			
			statistiquesParProduit.getChildren().add(caseStatistiqueParProduit);
		}
		
		ScrollPane affichageStatistiquesParProduit = new ScrollPane();
		affichageStatistiquesParProduit.setContent(statistiquesParProduit);
		affichageStatistiquesParProduit.setPrefHeight(200);
		affichageStatistiquesParProduit.setPrefWidth(430);
		
		BorderPane grilleStatistiquesParProduit = new BorderPane();
		BorderPane.setAlignment(titreParProduit, Pos.CENTER);
		grilleStatistiquesParProduit.setTop(titreParProduit);
		grilleStatistiquesParProduit.setCenter(affichageStatistiquesParProduit);
		
		Label titreParCategorie = new Label("Par Catégorie");
		
		VBox statistiquesParCategorie = new VBox();
		for(int i = 0; i<10; i++) {
			GridPane donneesStatistiquesParCategorie = new GridPane();
			donneesStatistiquesParCategorie.add(new Label("moy"), 0, 0);
			donneesStatistiquesParCategorie.add(new Label("max"), 1, 0);
			donneesStatistiquesParCategorie.add(new Label("meilleur produit"), 2, 0);
			donneesStatistiquesParCategorie.add(new Label("840"), 0, 1);
			donneesStatistiquesParCategorie.add(new Label("10"), 1, 1);
			donneesStatistiquesParCategorie.add(new Label("Gilet Jaune"), 2, 1);
			donneesStatistiquesParCategorie.setAlignment(Pos.CENTER);
			donneesStatistiquesParCategorie.setHgap(10);
			
			Label categorie = new Label("Arme");
			
			BorderPane caseStatistiqueParCategorie = new BorderPane();
			BorderPane.setAlignment(categorie, Pos.CENTER);
			BorderPane.setMargin(categorie, new Insets(10));
			caseStatistiqueParCategorie.setLeft(categorie);
			caseStatistiqueParCategorie.setCenter(donneesStatistiquesParCategorie);
			caseStatistiqueParCategorie.setPrefWidth(400);
			caseStatistiqueParCategorie.setPrefHeight(50);
			
			statistiquesParCategorie.getChildren().add(caseStatistiqueParCategorie);
		}
		
		ScrollPane affichageStatistiquesParCategorie = new ScrollPane();
		affichageStatistiquesParCategorie.setContent(statistiquesParCategorie);
		affichageStatistiquesParCategorie.setPrefHeight(200);
		affichageStatistiquesParCategorie.setPrefWidth(400);
		
		BorderPane grilleStatistiquesParCategorie = new BorderPane();
		BorderPane.setAlignment(titreParCategorie, Pos.CENTER);
		grilleStatistiquesParCategorie.setTop(titreParCategorie);
		grilleStatistiquesParCategorie.setCenter(affichageStatistiquesParCategorie);
		
		Label titreParRegion = new Label("Par Région");

		VBox statistiquesParRegion = new VBox();
		for(int i = 0; i<10; i++) {			
			Label region = new Label("France");
			
			BorderPane caseStatistiqueParRegion = new BorderPane();
			BorderPane.setAlignment(region, Pos.CENTER);
			BorderPane.setMargin(region, new Insets(10));
			caseStatistiqueParRegion.setLeft(region);
			caseStatistiqueParRegion.setCenter(new Label("60 millions d'acheteurs"));
			caseStatistiqueParRegion.setPrefWidth(400);
			caseStatistiqueParRegion.setPrefHeight(50);
			
			statistiquesParRegion.getChildren().add(caseStatistiqueParRegion);
		}
		
		ScrollPane affichageStatistiquesParRegion = new ScrollPane();
		affichageStatistiquesParRegion.setContent(statistiquesParRegion);
		affichageStatistiquesParRegion.setPrefHeight(200);
		affichageStatistiquesParRegion.setPrefWidth(430);
		
		BorderPane grilleStatistiquesParRegion = new BorderPane();
		BorderPane.setAlignment(titreParRegion, Pos.CENTER);
		grilleStatistiquesParRegion.setTop(titreParRegion);
		grilleStatistiquesParRegion.setCenter(affichageStatistiquesParRegion);
		
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
		
		BorderPane elementCentral = new BorderPane();
		elementCentral.setTop(affichageAnnee);
		elementCentral.setCenter(affichageStatistiques);
		
		BorderPane fenetrePrincipale = new BorderPane();
		fenetrePrincipale.setTop(hautApplication);
		fenetrePrincipale.setCenter(elementCentral);
		
		stade.setScene(new Scene(fenetrePrincipale, 900, 500));
		stade.setTitle("Volet Entreprise Vente Achat");
		stade.show();
		
	}

	private void initialiserDonneesParAnnee(int annee) {

	}
}