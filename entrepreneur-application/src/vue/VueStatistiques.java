package vue;

import java.util.ArrayList;
import java.util.Calendar;

import donnee.MySqlDAO;
import modele.StatistiqueCategorie;
import modele.StatistiqueMois;
import modele.StatistiqueProduit;
import modele.StatistiqueRegion;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VueStatistiques extends Application {
	
	private MySqlDAO accesseur;
	
	private TableView<StatistiqueMois> tableStatitistiquesMois;
	private TableView<StatistiqueProduit> tableStatistiquesProduit;
	private TableView<StatistiqueCategorie> tableStatistiquesCategorie;
	private TableView<StatistiqueRegion> tableStatistiquesRegion;

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		ArrayList<Integer> annees = new ArrayList<>();
		for (int annee = Calendar.getInstance().get(Calendar.YEAR); annee > 2000 ; annee--) {
			annees.add(annee);
		}
		ComboBox choixAnnees = new ComboBox();
		choixAnnees.getItems().addAll(annees);
		choixAnnees.setValue(Calendar.getInstance().get(Calendar.YEAR));
		choixAnnees.setVisibleRowCount(4);
		Button changerDate = new Button("Valider");
		
		GridPane affichageAnnee = new GridPane();		
		affichageAnnee.add(labelAnnee, 0, 0);
		affichageAnnee.add(choixAnnees, 1, 0);
		affichageAnnee.add(changerDate, 2, 0);
		affichageAnnee.setPadding(new Insets(5));
		GridPane.setMargin(changerDate, new Insets(10));
		
		tableStatitistiquesMois = new TableView<StatistiqueMois>();
		
		TableColumn<StatistiqueMois, String> statistiquesMoisTitre = new TableColumn<StatistiqueMois, String>("Par mois");
		TableColumn<StatistiqueMois, String> statistiquesMoisColonneMois = new TableColumn<StatistiqueMois, String>("Mois");
		TableColumn<StatistiqueMois, Float> statistiquesMoisColonneMoyenne = new TableColumn<StatistiqueMois, Float>("Moyenne");
		TableColumn<StatistiqueMois, Float> statistiquesMoisColonneMaximum = new TableColumn<StatistiqueMois, Float>("Maximum");
		TableColumn<StatistiqueMois, String> statistiquesMoisColonneMeilleurProduit = new TableColumn<StatistiqueMois, String>("Meilleur Produit");
		
		statistiquesMoisColonneMois.setCellValueFactory(new PropertyValueFactory<>("mois"));
		statistiquesMoisColonneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
		statistiquesMoisColonneMaximum.setCellValueFactory(new PropertyValueFactory<>("maximum"));
		statistiquesMoisColonneMeilleurProduit.setCellValueFactory(new PropertyValueFactory<>("meilleurProduit"));
				
		statistiquesMoisColonneMois.setStyle( "-fx-alignment: CENTER;");
		statistiquesMoisColonneMoyenne.setStyle( "-fx-alignment: CENTER;");
		statistiquesMoisColonneMaximum.setStyle( "-fx-alignment: CENTER;");
		statistiquesMoisColonneMeilleurProduit.setStyle( "-fx-alignment: CENTER;");

		statistiquesMoisTitre.getColumns().addAll(statistiquesMoisColonneMois, statistiquesMoisColonneMoyenne, 
				statistiquesMoisColonneMaximum, statistiquesMoisColonneMeilleurProduit);
		tableStatitistiquesMois.getColumns().addAll(statistiquesMoisTitre);
		tableStatitistiquesMois.setPrefWidth(500);
		tableStatitistiquesMois.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tableStatistiquesProduit = new TableView<StatistiqueProduit>();
		
		TableColumn<StatistiqueProduit, String> statistiquesProduitTitre = new TableColumn<StatistiqueProduit, String>("Par produit");
		TableColumn<StatistiqueProduit, String> statistiquesProduitColonneProduit = new TableColumn<StatistiqueProduit, String>("Produit"); 
		TableColumn<StatistiqueProduit, Float> statistiquesProduitColonneMoyenne = new TableColumn<StatistiqueProduit, Float>("Moyenne"); 
		TableColumn<StatistiqueProduit, Float> statistiquesProduitColonneMaximum = new TableColumn<StatistiqueProduit, Float>("Maximum"); 
		TableColumn<StatistiqueProduit, String> statistiquesProduitColonneMeilleurMois = new TableColumn<StatistiqueProduit, String>("Meilleur Mois"); 
		
		statistiquesProduitColonneProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));
		statistiquesProduitColonneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
		statistiquesProduitColonneMaximum.setCellValueFactory(new PropertyValueFactory<>("maximum"));
		statistiquesProduitColonneMeilleurMois.setCellValueFactory(new PropertyValueFactory<>("meilleurMois"));
		
		statistiquesProduitColonneProduit.setStyle( "-fx-alignment: CENTER;");
		statistiquesProduitColonneMoyenne.setStyle( "-fx-alignment: CENTER;");
		statistiquesProduitColonneMaximum.setStyle( "-fx-alignment: CENTER;");
		statistiquesProduitColonneMeilleurMois.setStyle( "-fx-alignment: CENTER;");
		
		statistiquesProduitTitre.getColumns().addAll(statistiquesProduitColonneProduit, statistiquesProduitColonneMoyenne,
				statistiquesProduitColonneMaximum, statistiquesProduitColonneMeilleurMois);
		tableStatistiquesProduit.getColumns().addAll(statistiquesProduitTitre);
		tableStatistiquesProduit.setPrefWidth(500);
		tableStatistiquesProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tableStatistiquesCategorie = new TableView<StatistiqueCategorie>();
		
		TableColumn<StatistiqueCategorie, String> statistiquesCategorieTitre = new TableColumn<StatistiqueCategorie, String>("Par catégorie");
		TableColumn<StatistiqueCategorie, String> statistiquesCategorieColonneCategorie = new TableColumn<StatistiqueCategorie, String>("Catégorie"); 
		TableColumn<StatistiqueCategorie, Float> statistiquesCategorieColonneMoyenne = new TableColumn<StatistiqueCategorie, Float>("Moyenne"); 
		TableColumn<StatistiqueCategorie, Float> statistiquesCategorieColonneMaximum = new TableColumn<StatistiqueCategorie, Float>("Maximum"); 
		TableColumn<StatistiqueCategorie, String> statistiquesCategorieColonneMeilleurProduit = new TableColumn<StatistiqueCategorie, String>("Meilleur Produit"); 
		
		statistiquesCategorieColonneCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
		statistiquesCategorieColonneMoyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
		statistiquesCategorieColonneMaximum.setCellValueFactory(new PropertyValueFactory<>("maximum"));
		statistiquesCategorieColonneMeilleurProduit.setCellValueFactory(new PropertyValueFactory<>("meilleurProduit"));
		
		statistiquesCategorieColonneCategorie.setStyle( "-fx-alignment: CENTER;");
		statistiquesCategorieColonneMoyenne.setStyle( "-fx-alignment: CENTER;");
		statistiquesCategorieColonneMaximum.setStyle( "-fx-alignment: CENTER;");
		statistiquesCategorieColonneMeilleurProduit.setStyle( "-fx-alignment: CENTER;");
		
		statistiquesCategorieTitre.getColumns().addAll(statistiquesCategorieColonneCategorie, statistiquesCategorieColonneMoyenne,
				statistiquesCategorieColonneMaximum, statistiquesCategorieColonneMeilleurProduit);
		tableStatistiquesCategorie.getColumns().addAll(statistiquesCategorieTitre);
		tableStatistiquesCategorie.setPrefWidth(500);
		tableStatistiquesCategorie.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tableStatistiquesRegion = new TableView<StatistiqueRegion>();
		
		TableColumn<StatistiqueRegion, String> statistiquesRegionTitre = new TableColumn<StatistiqueRegion, String>("Par région");
		TableColumn<StatistiqueRegion, String> statistiquesRegionColonneRegion = new TableColumn<StatistiqueRegion, String>("Région"); 
		TableColumn<StatistiqueRegion, Integer> statistiquesRegionColonneNombreAchats = new TableColumn<StatistiqueRegion, Integer>("Nombre d'achats");
		
		statistiquesRegionColonneRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
		statistiquesRegionColonneNombreAchats.setCellValueFactory(new PropertyValueFactory<>("nombreAcheteurs"));
				
		statistiquesRegionColonneRegion.setStyle( "-fx-alignment: CENTER;");
		statistiquesRegionColonneNombreAchats.setStyle( "-fx-alignment: CENTER;");
		
		statistiquesRegionTitre.getColumns().addAll(statistiquesRegionColonneRegion, statistiquesRegionColonneNombreAchats);
		tableStatistiquesRegion.getColumns().addAll(statistiquesRegionTitre);
		tableStatistiquesRegion.setPrefWidth(500);
		tableStatistiquesRegion.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		GridPane affichageStatistiques = new GridPane();		
		affichageStatistiques.add(tableStatitistiquesMois, 0, 0);
		affichageStatistiques.add(tableStatistiquesProduit, 0, 1);
		affichageStatistiques.add(tableStatistiquesCategorie, 1, 0);
		affichageStatistiques.add(tableStatistiquesRegion, 1, 1);
		affichageStatistiques.setAlignment(Pos.CENTER);
		affichageStatistiques.setHgap(10);
		affichageStatistiques.setVgap(10);
		
		BorderPane elementCentral = new BorderPane();
		elementCentral.setTop(affichageAnnee);
		elementCentral.setCenter(affichageStatistiques);
		elementCentral.setBottom(new Label());
		
		BorderPane fenetrePrincipale = new BorderPane();
		fenetrePrincipale.setTop(hautApplication);
		fenetrePrincipale.setCenter(elementCentral);
		
		initialiserDonnees(Calendar.getInstance().get(Calendar.YEAR));
				
		stade.setScene(new Scene(fenetrePrincipale, 1050, 800));
		stade.setTitle("Volet Entreprise Vente Achat");
		stade.show();
		
	}

	private void initialiserDonnees(int annee) {
		ObservableList<StatistiqueMois> listeStatistiqueMois = accesseur.recupererStatistiquesMoisParAnnee(annee);
		tableStatitistiquesMois.setItems(listeStatistiqueMois);
		
		ObservableList<StatistiqueProduit> listeStatistiqueProduit = accesseur.recupererStatistiquesProduitsParAnnee(annee);
		tableStatistiquesProduit.setItems(listeStatistiqueProduit);
		
		ObservableList<StatistiqueCategorie> listeStatistiqueCategorie = accesseur.recupererStatistiquesCategoriesParAnnee(annee);
		tableStatistiquesCategorie.setItems(listeStatistiqueCategorie);
		
		ObservableList<StatistiqueRegion> listeStatistiqueRegion = accesseur.recupererStatistiquesRegionParAnnee(annee);
		tableStatistiquesRegion.setItems(listeStatistiqueRegion);
	}
}