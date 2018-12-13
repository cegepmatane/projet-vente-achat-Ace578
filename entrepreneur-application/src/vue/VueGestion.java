package vue;

import java.util.ArrayList;
import java.util.Calendar;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class VueGestion extends Scene {
	
	private MySqlDAO accesseur;
	
	private ControleurStatistiques controleurStatistiques;

	private BorderPane fenetrePrincipale;

	
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
		
		ComboBox choixCategories = new ComboBox();
		choixCategories.getItems().addAll(categorie);
		choixCategories.setValue(categorie.get(0));
		choixCategories.setVisibleRowCount(4);
		
		Button changerCategorie = new Button("Valider");
		changerCategorie.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Changement catégorie");				
			}
		});		
		
		GridPane affichageCategorie = new GridPane();	
		affichageCategorie.add(labelCategorie, 0, 0);
		affichageCategorie.add(choixCategories, 1, 0);
		affichageCategorie.add(changerCategorie, 2, 0);
		affichageCategorie.setPadding(new Insets(5));
		GridPane.setMargin(changerCategorie, new Insets(10));
		
		BorderPane elementCentral = new BorderPane();
		elementCentral.setTop(affichageCategorie);
		elementCentral.setCenter(new Label());
		elementCentral.setBottom(new Label());
		
		fenetrePrincipale.setCenter(elementCentral);
	}

}
