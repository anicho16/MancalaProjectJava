/**
 * 
 */

package edu.westga.cs6910.mancala.view;

import edu.westga.cs6910.mancala.model.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Defines the pane that allows the user to choose the number of stones in each
 * starting pit.
 * 
 * @author Amber Nicholas
 * @version 6.25.22
 */
public class PitChooserPane extends GridPane {
	private Game theGame;

	/**
	 * Creates a new status pane that observes the specified game.
	 * 
	 * @param theGame the model object from which this pane gets its data
	 * 
	 * @requires theGame != null
	 */
	public PitChooserPane(Game theGame) {
		this.theGame = theGame;

		this.buildPane();
	}

	private void buildPane() {
		BorderPane pane = new BorderPane();
		ComboBox<String> stoneChooser = new ComboBox<>();

		BorderPane paneForComboBox = new BorderPane();
		paneForComboBox.setLeft(new Label("Select number of starting stones in each pit: "));
		paneForComboBox.setCenter(stoneChooser);
		pane.setBottom(paneForComboBox);
		stoneChooser.setPrefWidth(40);
		stoneChooser.setValue("1");

		String[] stoneChoices = { "1", "2", "3", "4", "5" };
		ObservableList<String> choices = FXCollections.observableArrayList(stoneChoices);
		stoneChooser.getItems().addAll(choices);

		stoneChooser.setOnAction(e -> this.theGame.setStartingStonesInPit(choices.indexOf(stoneChooser.getValue()) + 1));

		this.add(pane, 0, 0);
	}

}
