package edu.westga.cs6910.mancala.view;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.Player;
import edu.westga.cs6910.mancala.model.strategies.FarStrategy;
import edu.westga.cs6910.mancala.model.strategies.NearStrategy;
import edu.westga.cs6910.mancala.model.strategies.RandomStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Defines a GUI for the Mancala game. Started by CS6910. Fill your name into
 * Javadoc below
 * 
 * @author Amber Nicholas
 * @version 6.5.22
 * 
 */
public class MancalaPane extends BorderPane {
	private Game theGame;
	private GridPane pnContent;
	private HumanPane pnHumanPlayer;
	private ComputerPane pnComputerPlayer;
	private StatusPane pnGameInfo;
	private Pane pnChooseFirstPlayer;
	private PitChooserPane pnStoneChooser;

	/**
	 * Creates a pane object to provide the view for the specified Game model
	 * object.
	 * 
	 * @param theGame the domain model object representing the Mancala game
	 * 
	 * @requires theGame != null
	 * @ensures the pane is displayed properly
	 */
	public MancalaPane(Game theGame) {
		this.theGame = theGame;

		this.pnContent = new GridPane();

		this.addMenu();
		this.addPitChooserPane();
		this.addFirstPlayerChooserPane(theGame);
		this.addComputerPane();
		this.addHumanPane();
		this.addStatusPane();

		this.setCenter(this.pnContent);
	}

	private void showRulesAlerts() {
		Alert tookExtraStonesOrTurn = new Alert(AlertType.INFORMATION);
		tookExtraStonesOrTurn
				.setContentText("Special Rule: If your last stone lands in an empty pit on your side of the board, "
						+ "move the stone and the stones in the opposite pit to your Mancala (store).\r\r"
						+ "Special Rule: If your last stone lands in your Mancala, take another turn.");
		tookExtraStonesOrTurn.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		tookExtraStonesOrTurn.show();
	}

	private void addMenu() {
		BorderPane root = new BorderPane();

		MenuBar gameMenuBar = new MenuBar();
		gameMenuBar.prefWidthProperty().bind(this.widthProperty());
		root.setTop(gameMenuBar);

		Menu gameMenu = this.createGameMenu();
		Menu computerPlayerMenu = this.createComputerPlayerMenu();

		gameMenuBar.getMenus().addAll(gameMenu, computerPlayerMenu);

		this.pnContent.add(gameMenuBar, 0, 0);
	}

	private Menu createComputerPlayerMenu() {
		Menu computerPlayerMenu = new Menu("Computer Player");
		computerPlayerMenu.setMnemonicParsing(true);

		MenuItem nearMenuItem = new MenuItem("Near        Ctrl+N");
		nearMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+N"));

		MenuItem farMenuItem = new MenuItem("Far           Ctrl+A");
		farMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+A"));

		MenuItem randomMenuItem = new MenuItem("Random  Ctrl+R");
		randomMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+R"));

		nearMenuItem.setOnAction((ActionEvent t) -> {
			this.theGame.getComputerPlayer().setStrategy(new NearStrategy());
		});

		farMenuItem.setOnAction((ActionEvent t) -> {
			this.theGame.getComputerPlayer().setStrategy(new FarStrategy());
		});

		randomMenuItem.setOnAction((ActionEvent t) -> {
			this.theGame.getComputerPlayer().setStrategy(new RandomStrategy());
		});

		computerPlayerMenu.getItems().addAll(nearMenuItem, farMenuItem, randomMenuItem);

		return computerPlayerMenu;
	}

	private Menu createGameMenu() {
		Menu gameMenu = new Menu("Game");
		gameMenu.setMnemonicParsing(true);

		MenuItem exitMenuItem = new MenuItem("Exit  Ctrl+X");
		exitMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+X"));

		exitMenuItem.setOnAction((ActionEvent t) -> {
			System.exit(0);
		});

		gameMenu.getItems().addAll(exitMenuItem);
		return gameMenu;
	}

	private void addPitChooserPane() {
		HBox pitChoiceBox = new HBox();
		pitChoiceBox.setAlignment(Pos.CENTER);
		pitChoiceBox.getStyleClass().add("pane-border");
		this.pnStoneChooser = new PitChooserPane(this.theGame);
		pitChoiceBox.getChildren().add(this.pnStoneChooser);

		this.pnContent.add(pitChoiceBox, 0, 1);
	}

	private void addFirstPlayerChooserPane(Game theGame) {
		HBox topBox = new HBox();
		topBox.getStyleClass().add("pane-border");
		this.pnChooseFirstPlayer = new NewGamePane(theGame);
		topBox.getChildren().add(this.pnChooseFirstPlayer);
		this.pnContent.add(topBox, 0, 2);
	}

	private void addComputerPane() {
		HBox computerBox = new HBox();
		computerBox.getStyleClass().add("pane-border");
		this.pnComputerPlayer = new ComputerPane(this.theGame);
		computerBox.getChildren().add(this.pnComputerPlayer);

		this.pnContent.add(computerBox, 0, 3);
	}

	private void addHumanPane() {
		HBox humanBox = new HBox();
		humanBox.getStyleClass().add("pane-border");
		this.pnHumanPlayer = new HumanPane(this.theGame);
		humanBox.getChildren().add(this.pnHumanPlayer);

		this.pnContent.add(humanBox, 0, 4);
	}

	private void addStatusPane() {
		HBox statusBox = new HBox();
		statusBox.setAlignment(Pos.CENTER);
		statusBox.getStyleClass().add("pane-border");
		this.pnGameInfo = new StatusPane(this.theGame);
		statusBox.getChildren().add(this.pnGameInfo);

		this.pnContent.add(statusBox, 0, 5);
	}

	/*
	 * Defines the panel in which the user selects which Player plays first.
	 */
	private final class NewGamePane extends GridPane {
		private RadioButton radHumanPlayer;
		private RadioButton radComputerPlayer;

		private Game theGame;
		private Player theHuman;
		private Player theComputer;

		private NewGamePane(Game theGame) {
			this.theGame = theGame;

			this.theHuman = this.theGame.getHumanPlayer();
			this.theComputer = this.theGame.getComputerPlayer();

			this.buildPane();
		}

		private void buildPane() {
			this.setHgap(20);

			this.radHumanPlayer = new RadioButton(this.theHuman.getName() + " first");
			this.radHumanPlayer.setOnAction(new HumanFirstListener());

			this.radComputerPlayer = new RadioButton(this.theComputer.getName() + " first");
			this.radComputerPlayer.setOnAction(new ComputerFirstListener());

			ToggleGroup players = new ToggleGroup();
			this.radComputerPlayer.setToggleGroup(players);
			this.radHumanPlayer.setToggleGroup(players);

			this.add(this.radComputerPlayer, 1, 0);
			this.add(this.radHumanPlayer, 0, 0);

		}

		/*
		 * Defines the listener for computer player first button.
		 */
		private class ComputerFirstListener implements EventHandler<ActionEvent> {
			@Override
			/**
			 * Enables the ComputerPlayerPanel and starts a new game. Event handler for a
			 * click in the computerPlayerButton.
			 */
			public void handle(ActionEvent arg0) {
				MancalaPane.this.pnComputerPlayer.setDisable(false);
				MancalaPane.this.pnChooseFirstPlayer.setDisable(true);
				MancalaPane.this.pnStoneChooser.setDisable(true);
				MancalaPane.this.showRulesAlerts();
				MancalaPane.this.theGame.startNewGame(NewGamePane.this.theComputer);
			}
		}

		/*
		 * Defines the listener for human player first button.
		 */
		private class HumanFirstListener implements EventHandler<ActionEvent> {
			/*
			 * Sets up user interface and starts a new game. Event handler for a click in
			 * the human player button.
			 */
			@Override
			public void handle(ActionEvent event) {
				MancalaPane.this.pnHumanPlayer.setDisable(false);
				MancalaPane.this.pnChooseFirstPlayer.setDisable(true);
				MancalaPane.this.pnStoneChooser.setDisable(true);
				MancalaPane.this.showRulesAlerts();
				MancalaPane.this.theGame.startNewGame(NewGamePane.this.theHuman);
			}
		}
	}
}
