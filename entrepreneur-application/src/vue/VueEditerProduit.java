package vue;

import action.ControleurStatistiques;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class VueEditerProduit extends Scene {
	
	private ControleurStatistiques controleurStatistiques;

	public VueEditerProduit() {
		super(new BorderPane(), 1050, 800);

	}

	public void setControleurStatistiques(ControleurStatistiques controleurStatistiques) {
		this.controleurStatistiques = controleurStatistiques;
	}

}