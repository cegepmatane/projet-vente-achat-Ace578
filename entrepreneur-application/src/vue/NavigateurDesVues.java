package vue;

import action.ControleurStatistiques;
import javafx.application.Application;
import javafx.stage.Stage;

public class NavigateurDesVues extends Application {
	
	private Stage stade;
	private VueStatistiques vueStatistiques = null;
	private VueGestion vueGestion = null;
	private VueEditerProduit vueEditerProduit = null;
	private ControleurStatistiques controleurStatistiques;
	
	public NavigateurDesVues() {
		this.vueStatistiques = new VueStatistiques();
		this.vueGestion = new VueGestion();
		this.vueEditerProduit = new VueEditerProduit();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stade = stage;
		stade.setScene(null);
		controleurStatistiques = ControleurStatistiques.getInstance();
		controleurStatistiques.activerVues(this);
		vueStatistiques.setControleurStatistiques(controleurStatistiques);
		vueGestion.setControleurStatistiques(controleurStatistiques);
		vueEditerProduit.setControleurStatistiques(controleurStatistiques);
	}
	
	public VueStatistiques getVueStatistiques() {
		return vueStatistiques;
	}

	public VueGestion getVueGestion() {
		return vueGestion;
	}

	public void naviguerVersVueStatistiques() {
		stade.setScene(vueStatistiques);
		stade.show();
	}

	public void naviguerVersVueGestion() {
		stade.setScene(vueGestion);
		stade.show();
	}
}
