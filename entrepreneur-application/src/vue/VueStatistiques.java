package vue;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

		BorderPane panneau = new BorderPane();

		BorderPane.setAlignment(hautApplication, Pos.CENTER);
		panneau.setTop(hautApplication);
		
		panneau.setStyle("-fx-border-color: black");
		
		stade.setScene(new Scene(panneau, 800, 400));
		stade.setTitle("Volet Entreprise Vente Achat");
		
		stade.show();
		
	}
}