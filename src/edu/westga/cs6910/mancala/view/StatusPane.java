package edu.westga.cs6910.mancala.view;

import edu.westga.cs6910.mancala.model.Game;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Defines the pane that displays the total number of stones in each store. It
 * also displays the winner (or tie) at the end of the game
 * 
 * @author CS6910
 * @version Summer 2022
 */
public class StatusPane extends GridPane implements InvalidationListener {
	private Game theGame;
	private Label lblStatus;
	private Label specialMessage;

	/**
	 * Creates a new status pane that observes the specified game.
	 * 
	 * @param theGame the model object from which this pane gets its data
	 * 
	 * @requires theGame != null
	 */
	public StatusPane(Game theGame) {
		this.theGame = theGame;

		this.theGame.addListener(this);

		this.buildPane();
	}

	private void buildPane() {
		HBox topBox = new HBox();
		topBox.getStyleClass().add("box-center");
		topBox.getStyleClass().add("box-padding");
		topBox.getChildren().add(new Label("~~ Game Info ~~"));
		this.add(topBox, 0, 0);

		HBox middleBox = new HBox();
		middleBox.getStyleClass().add("box-padding");
		this.lblStatus = new Label(this.theGame.toString());
		middleBox.getChildren().add(this.lblStatus);
		
		HBox bottomBox = new HBox();
		bottomBox.getStyleClass().add("box-padding");
		this.specialMessage = new Label(this.theGame.getSpecialMessage());
		this.specialMessage.setTextFill(Color.PINK);
		this.specialMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        this.specialMessage.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(-10))));

		bottomBox.getChildren().add(this.specialMessage);
		
		this.add(middleBox, 0, 1);
		this.add(bottomBox, 0, 2);
	}

	@Override
	public void invalidated(Observable observable) {
		this.lblStatus.setText(this.theGame.toString());
		this.specialMessage.setText("\n" + this.theGame.getSpecialMessage());
	}
}
