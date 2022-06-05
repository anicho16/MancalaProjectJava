package edu.westga.cs6910.mancala.view;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Defines the pane that lets the user tell the computer player to
 * take its turn and that displays the setup of this player's side
 * of the board
 * 
 */
public class ComputerPane extends GridPane implements InvalidationListener {

	private Button btnTakeTurn;
	
	private ComputerPlayer theComputer;
	private Game theGame;
	
	/**
	 * Creates a new ComputerPane that observes the specified game. 
	 * 
	 * @param theGame	the model object from which this pane gets its data
	 * 
	 * @requires 	theGame != null
	 */
	public ComputerPane(Game theGame) {
		this.theGame = theGame;
		
		// TODO: Add this object as an listener of the Game.
		
		this.theComputer = this.theGame.getComputerPlayer();
		
		this.buildPane();
	}
	
	private void buildPane() {
		// TODO: Using the other pane classes as a model, build this pane.

	}
	
	private void createUserInteractionArea() {
		GridPane interactionPane = new GridPane();
		interactionPane.getStyleClass().add("pane-border");
		interactionPane.getStyleClass().add("bg-highlight-style");	
		
		this.btnTakeTurn = new Button("Take Turn");
		this.btnTakeTurn.setOnAction(new TakeTurnListener());
		
		interactionPane.getChildren().add(this.btnTakeTurn);
		
		this.add(interactionPane, this.theGame.getBoardSize() / 2 + 1, 1);
	}

	@Override
	public void invalidated(Observable arg0) {
		// TODO: Disable this Pane if it is no longer the computer's turn, enable it if
		// it is the computer's turn

	}

	/* 
	 * Defines the listener for takeTurnButton.
	 */
	private class TakeTurnListener implements EventHandler<ActionEvent> {

		/* 
		 * Tells the Game to have its current player (i.e., the computer player)
		 * take its turn.	
		 * 
		 * @see javafx.event.EventHandler#handle(T-extends-javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent arg0) {
			// TODO: if the game isn't finished: 
			//		 - Tell theGame to play a move.  Because this is
			//		   the computer playing, just pass -1 as the 
			//		   pit number

		}
	}
}