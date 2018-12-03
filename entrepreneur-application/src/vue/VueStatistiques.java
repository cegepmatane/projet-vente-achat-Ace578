package vue;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
		hautApplication.setStyle("-fx-border-color: blue");
		
		BorderPane elementCentral = new BorderPane();
		
		Label labelAnnee = new Label("Année : ");
		DatePicker annee = new DatePicker();
		
		GridPane affichageApplication = new GridPane();		
		
		affichageApplication.add(labelAnnee, 0, 0);
		affichageApplication.add(annee, 1, 0);
		
		affichageApplication.setPadding(new Insets(5));
		
		elementCentral.setTop(affichageApplication);
		elementCentral.setStyle("-fx-border-color: red");

		BorderPane fenetrePrincipale = new BorderPane();

		BorderPane.setAlignment(hautApplication, Pos.CENTER);
		fenetrePrincipale.setTop(hautApplication);
		fenetrePrincipale.setCenter(elementCentral);
		
		fenetrePrincipale.setStyle("-fx-border-color: black");
		
		stade.setScene(new Scene(fenetrePrincipale, 800, 400));
		stade.setTitle("Volet Entreprise Vente Achat");
		
		stade.show();
		
	}
}