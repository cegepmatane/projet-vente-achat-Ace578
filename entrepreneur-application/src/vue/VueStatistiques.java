package vue;

import java.util.Calendar;
import java.util.List;

import donnee.MySqlDAO;
import modele.StatistiqueCategorie;
import modele.StatistiqueMois;
import modele.StatistiqueProduit;
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
	private GridPane donneesStatistiquesParMois,donneesStatistiquesParProduit, donneesStatistiquesParCategorie;
	private BorderPane caseStatistiqueParMois, caseStatistiqueParProduit, caseStatistiquesParCategorie;
	private VBox statistiquesParMois, statistiquesParProduit, statistiquesParCategorie;

	@Override
	public void start(Stage stade) throws Exception {
		
		this.accesseur = new MySqlDAO();
						
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
		
		statistiquesParMois = new VBox();
		
		ScrollPane affichageStatistiquesParMois = new ScrollPane();
		affichageStatistiquesParMois.setContent(statistiquesParMois);
		affichageStatistiquesParMois.setPrefHeight(200);
		affichageStatistiquesParMois.setPrefWidth(430);

		BorderPane grilleStatistiquesParMois = new BorderPane();
		BorderPane.setAlignment(titreParMois, Pos.CENTER);
		grilleStatistiquesParMois.setTop(titreParMois);
		grilleStatistiquesParMois.setCenter(affichageStatistiquesParMois);
		
		Label titreParProduit = new Label("Par Produit (les 5 meilleurs)");
		
		statistiquesParProduit = new VBox();
		
		ScrollPane affichageStatistiquesParProduit = new ScrollPane();
		affichageStatistiquesParProduit.setContent(statistiquesParProduit);
		affichageStatistiquesParProduit.setPrefHeight(200);
		affichageStatistiquesParProduit.setPrefWidth(430);
		
		BorderPane grilleStatistiquesParProduit = new BorderPane();
		BorderPane.setAlignment(titreParProduit, Pos.CENTER);
		grilleStatistiquesParProduit.setTop(titreParProduit);
		grilleStatistiquesParProduit.setCenter(affichageStatistiquesParProduit);
		
		Label titreParCategorie = new Label("Par Catégorie");
		
		statistiquesParCategorie = new VBox();
		
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
		
		initialiserDonneesParAnnee(Calendar.getInstance().get(Calendar.YEAR));
		
		stade.setScene(new Scene(fenetrePrincipale, 900, 500));
		stade.setTitle("Volet Entreprise Vente Achat");
		stade.show();
		
	}

	private void initialiserDonneesParAnnee(int annee) {
		List<StatistiqueMois> statistiquesMois = accesseur.recupererStatistiquesMoisParAnnee(annee);
		for (int iterateur = 0; iterateur<statistiquesMois.size(); iterateur++) {
			caseStatistiqueParMois = new BorderPane();
			caseStatistiqueParMois.setPrefWidth(400);
			caseStatistiqueParMois.setPrefHeight(50);
			
			donneesStatistiquesParMois = new GridPane();
			donneesStatistiquesParMois.setAlignment(Pos.CENTER);
			donneesStatistiquesParMois.setHgap(10);
			
			donneesStatistiquesParMois.add(new Label("moy"), 0, iterateur+1);
			donneesStatistiquesParMois.add(new Label("max"), 1, iterateur+1);
			donneesStatistiquesParMois.add(new Label("meilleur produit"), 2, iterateur+1);
			donneesStatistiquesParMois.add(new Label(""+statistiquesMois.get(iterateur).getMoyenneFloat()), 0, iterateur+2);
			donneesStatistiquesParMois.add(new Label(""+statistiquesMois.get(iterateur).getMaximumFloat()), 1, iterateur+2);
			donneesStatistiquesParMois.add(new Label(""+statistiquesMois.get(iterateur).getMeilleurProduit()), 2, iterateur+2);
			
			Label date = new Label(""+statistiquesMois.get(iterateur).getMois());
			BorderPane.setAlignment(date, Pos.CENTER);
			BorderPane.setMargin(date, new Insets(10));
			
			caseStatistiqueParMois.setLeft(date);
			caseStatistiqueParMois.setCenter(donneesStatistiquesParMois);
			statistiquesParMois.getChildren().add(caseStatistiqueParMois);
		}
		
		List<StatistiqueProduit> statistiquesProduit = accesseur.recupererStatistiquesProduitsParAnnee(annee);
		for (int iterateur = 0; iterateur<statistiquesProduit.size(); iterateur++) {
			caseStatistiqueParProduit = new BorderPane();
			caseStatistiqueParProduit.setPrefWidth(400);
			caseStatistiqueParProduit.setPrefHeight(50);
			
			donneesStatistiquesParProduit = new GridPane();
			donneesStatistiquesParProduit.setAlignment(Pos.CENTER);
			donneesStatistiquesParProduit.setHgap(10);
			
			donneesStatistiquesParProduit.add(new Label("moy"), 0, iterateur+1);
			donneesStatistiquesParProduit.add(new Label("max"), 1, iterateur+1);
			donneesStatistiquesParProduit.add(new Label("meilleur produit"), 2, iterateur+1);
			donneesStatistiquesParProduit.add(new Label(""+statistiquesProduit.get(iterateur).getMoyenne()), 0, iterateur+2);
			donneesStatistiquesParProduit.add(new Label(""+statistiquesProduit.get(iterateur).getMaximum()), 1, iterateur+2);
			donneesStatistiquesParProduit.add(new Label(""+statistiquesProduit.get(iterateur).getMeilleurMois()), 2, iterateur+2);
			
			Label produit = new Label(""+statistiquesProduit.get(iterateur).getProduit());
			BorderPane.setAlignment(produit, Pos.CENTER);
			BorderPane.setMargin(produit, new Insets(10));
			
			caseStatistiqueParProduit.setLeft(produit);
			caseStatistiqueParProduit.setCenter(donneesStatistiquesParProduit);
			statistiquesParProduit.getChildren().add(caseStatistiqueParProduit);
		}
		
		List<StatistiqueCategorie> statistiquesCategorie = accesseur.recupererStatistiquesCategoriesParAnnee(annee);
		for (int iterateur = 0; iterateur<statistiquesCategorie.size(); iterateur++) {
			caseStatistiquesParCategorie = new BorderPane();
			caseStatistiquesParCategorie.setPrefWidth(400);
			caseStatistiquesParCategorie.setPrefHeight(50);
			
			donneesStatistiquesParCategorie = new GridPane();
			donneesStatistiquesParCategorie.setAlignment(Pos.CENTER);
			donneesStatistiquesParCategorie.setHgap(10);
			
			donneesStatistiquesParCategorie.add(new Label("moy"), 0, iterateur+1);
			donneesStatistiquesParCategorie.add(new Label("max"), 1, iterateur+1);
			donneesStatistiquesParCategorie.add(new Label("meilleur produit"), 2, iterateur+1);
			donneesStatistiquesParCategorie.add(new Label(""+statistiquesCategorie.get(iterateur).getMoyenne()), 0, iterateur+2);
			donneesStatistiquesParCategorie.add(new Label(""+statistiquesCategorie.get(iterateur).getMaximum()), 1, iterateur+2);
			donneesStatistiquesParCategorie.add(new Label(""+statistiquesCategorie.get(iterateur).getMeilleurProduit()), 2, iterateur+2);
			
			Label categorie = new Label(""+statistiquesCategorie.get(iterateur).getCategorie());
			BorderPane.setAlignment(categorie, Pos.CENTER);
			BorderPane.setMargin(categorie, new Insets(10));
			
			caseStatistiquesParCategorie.setLeft(categorie);
			caseStatistiquesParCategorie.setCenter(donneesStatistiquesParCategorie);
			statistiquesParCategorie.getChildren().add(caseStatistiquesParCategorie);
		}
	}
}