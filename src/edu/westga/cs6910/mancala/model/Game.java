package edu.westga.cs6910.mancala.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Game represents a Mancala game. Started by CS6910. Fill your name into
 * Javadoc below
 * 
 * @author Amber Nicholas
 * @version 6.5.22
 */
public class Game implements Observable {
	private int[] theBoard;

	private ObjectProperty<Player> currentPlayerObject;
	private HumanPlayer theHuman;
	private ComputerPlayer theComputer;
	private int startingStonesInPit;
	private boolean landedInMancala;

	private Player theWinner;
	private boolean isGameOver;

	/**
	 * Creates a Mancala Game with the specified Players
	 * 
	 */
	public Game() {
		this.theHuman = new HumanPlayer("Human", this);
		this.theComputer = new ComputerPlayer(this);
		this.startingStonesInPit = 1;
		this.landedInMancala = false;

		this.currentPlayerObject = new SimpleObjectProperty<Player>();

		this.theBoard = new int[8];
	}

	/**
	 * Initializes the game for play.
	 * 
	 * @param firstPlayer the Player who takes the first turn
	 * 
	 * @require firstPlayer != null &&
	 * 
	 * @ensures whoseTurn().equals(firstPlayer)
	 */
	public void startNewGame(Player firstPlayer) {
		this.resetBoard();
		this.currentPlayerObject.setValue(firstPlayer);
	}

	/**
	 * Distributes the stones located in pitNumber to all subsequent pits, one at a
	 * time in counter-clockwise order. Checks if the last stone is dropped into an
	 * empty pit and is on the player's side of the board, the player takes all the
	 * stones in the opponents opposite side pit
	 * 
	 * @param pitNumber The pit number where the stones are to be taken
	 */
	public void distributeStonesFrom(int pitNumber) {
		if (pitNumber < 0) {
			throw new IllegalArgumentException("Pit number cannot be negative");
		}
		Player currentPlayer = this.currentPlayerObject.getValue();
		int[] stonesAndLastPit = { 0, 0 };
		int stonesLeft = this.theBoard[pitNumber];
		this.theBoard[pitNumber] = 0;
		int lastPitStoneDropped = 0;
		boolean mySideOfTheBoard = false;
		this.landedInMancala = false;
		if (currentPlayer.equals(this.theHuman) && stonesLeft > 0) {
			stonesAndLastPit = this.distributeHumanStones(pitNumber, stonesLeft);
			stonesLeft = stonesAndLastPit[0];
			lastPitStoneDropped = stonesAndLastPit[1];
			while (stonesLeft > 0) {
				stonesAndLastPit = this.distributeHumanStones(lastPitStoneDropped, stonesLeft);
				stonesLeft = stonesAndLastPit[0];
				lastPitStoneDropped = stonesAndLastPit[1];
			}
		} else {
			if (stonesLeft > 0) {
				stonesAndLastPit = this.distributeComputerStones(pitNumber, stonesLeft);
				stonesLeft = stonesAndLastPit[0];
				lastPitStoneDropped = stonesAndLastPit[1];
				while (stonesLeft > 0) {
					stonesAndLastPit = this.distributeComputerStones(lastPitStoneDropped, stonesLeft);
					stonesLeft = stonesAndLastPit[0];
					lastPitStoneDropped = stonesAndLastPit[1];
				}
			}
		}
		mySideOfTheBoard = this.checkIfMySideOfTheBoard(currentPlayer, lastPitStoneDropped);
		this.checkIfLandedInMancala(currentPlayer, lastPitStoneDropped);
		if (mySideOfTheBoard && this.theBoard[lastPitStoneDropped] == 1
				&& lastPitStoneDropped != this.theBoard.length - 1
				&& lastPitStoneDropped != this.theBoard.length / 2 - 1) {
			this.takeStonesAcrossFromLastPit(lastPitStoneDropped);
		}
	}

	private void checkIfLandedInMancala(Player currentPlayer, int lastPitStoneDropped) {
		if (currentPlayer.equals(this.theHuman) && lastPitStoneDropped == this.theBoard.length / 2 - 1) {
			this.landedInMancala = true;
		} else if (currentPlayer.equals(this.theComputer) && lastPitStoneDropped == this.theBoard.length - 1) {
			this.landedInMancala = true;
		}
	}

	private boolean checkIfMySideOfTheBoard(Player currentPlayer, int lastPitStoneDropped) {
		boolean mySideOfTheBoard = false;
		if (currentPlayer.equals(this.theHuman) && lastPitStoneDropped >= 0
				&& lastPitStoneDropped < this.theBoard.length / 2) {
			mySideOfTheBoard = true;
		} else if (currentPlayer.equals(this.theComputer) && lastPitStoneDropped >= this.theBoard.length / 2
				&& lastPitStoneDropped < this.theBoard.length) {
			mySideOfTheBoard = true;
		}
		return mySideOfTheBoard;
	}

	private void takeStonesAcrossFromLastPit(int lastPitStoneDropped) {
		Player currentPlayer = this.currentPlayerObject.getValue();
		int counter = 0;
		int pitAcrossFrom = 0;
		int playerStore = 0;

		if (currentPlayer.equals(this.theComputer)) {
			counter = lastPitStoneDropped - (this.theBoard.length / 2 - 1);
			pitAcrossFrom = Math.abs(counter * 2 - lastPitStoneDropped);
			playerStore = this.theBoard.length - 1;
		} else {
			counter = this.theBoard.length / 2;
			int acrossCalculation = (counter - 1) * 2;
			pitAcrossFrom = Math.abs(lastPitStoneDropped - acrossCalculation);
			playerStore = this.theBoard.length / 2 - 1;
		}

		this.theBoard[playerStore] += this.theBoard[lastPitStoneDropped] + this.theBoard[pitAcrossFrom];
		this.theBoard[pitAcrossFrom] = 0;
		this.theBoard[lastPitStoneDropped] = 0;
	}

	private int[] distributeHumanStones(int pitNumber, int stonesInPit) {
		int pitIncrease = 1;
		int wrapper = pitNumber;
		int lastPitStoneDropped = pitNumber;

		if (wrapper < this.theBoard.length - 2) {
			while (wrapper < this.theBoard.length - 2 && stonesInPit > 0) {
				wrapper++;
				this.theBoard[pitNumber + pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
				lastPitStoneDropped++;
			}
		}
		pitIncrease = 0;
		if (wrapper == this.theBoard.length - 2 && stonesInPit > 0) {
			while (wrapper > 0 && stonesInPit > 0) {
				this.theBoard[pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
				wrapper--;
				lastPitStoneDropped++;
			}
		}
		while (lastPitStoneDropped > this.theBoard.length - 1) {
			lastPitStoneDropped -= (this.theBoard.length - 1);
		}
		int[] stonesAndLastPit = { stonesInPit, lastPitStoneDropped };

		return stonesAndLastPit;
	}

	private int[] distributeComputerStones(int pitNumber, int stonesInPit) {
		int pitIncrease = 1;
		int wrapper = pitNumber;
		int lastPitStoneDropped = pitNumber;
		if (wrapper < this.theBoard.length - 1) {
			while (wrapper < this.theBoard.length - 1 && stonesInPit > 0) {
				wrapper++;
				this.theBoard[pitNumber + pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
				lastPitStoneDropped++;
			}
		}

		pitIncrease = 0;
		wrapper = 0;
		while (wrapper < this.theBoard.length / 2 - 1 && stonesInPit > 0) {
			this.theBoard[pitIncrease] += 1;
			pitIncrease++;
			stonesInPit--;
			wrapper++;
			lastPitStoneDropped++;
		}
		pitIncrease++;
		while (wrapper > 0 && stonesInPit > 0) {
			this.theBoard[pitIncrease] += 1;
			pitIncrease++;
			stonesInPit--;
			wrapper--;
			lastPitStoneDropped++;
		}
		while (lastPitStoneDropped > this.theBoard.length - 1) {
			lastPitStoneDropped -= (this.theBoard.length - 1);
		}
		int[] stonesAndLastPit = { stonesInPit, lastPitStoneDropped };

		return stonesAndLastPit;
	}

	/**
	 * Returns the number of slots (pits and stores) on the board
	 * 
	 * @return The number of slots on the board
	 */
	public int getBoardSize() {
		return this.theBoard.length;
	}

	/**
	 * Returns the computer Player object.
	 * 
	 * @return the computer Player
	 */
	public ComputerPlayer getComputerPlayer() {
		return this.theComputer;
	}

	/**
	 * Returns the Player whose turn it is.
	 * 
	 * @return the current Player
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayerObject.getValue();
	}

	/**
	 * Returns the human Player object.
	 * 
	 * @return the human Player
	 */
	public HumanPlayer getHumanPlayer() {
		return this.theHuman;
	}

	/**
	 * Returns whether the game has completed yet or not
	 * 
	 * @return true iff the game is over; false otherwise
	 */
	public boolean getIsGameOver() {
		return this.isGameOver;
	}

	/**
	 * Returns the number of stones in the specified pit
	 * 
	 * @param pitNumber The pit location that is potentially holding stones
	 * @return The number of stones in the specified pit
	 */
	public int getStones(int pitNumber) {
		if (pitNumber < 0) {
			throw new IllegalArgumentException("Pit number cannot be negative");
		}
		return this.theBoard[pitNumber];
	}

	/**
	 * Conducts a move in the game, allowing the appropriate Player to take a turn.
	 * Has no effect if the game is over.
	 * 
	 * @param pitChoice The pit number where the stones will be taken from
	 * 
	 * @requires !isGameOver()
	 * 
	 * @ensures !whoseTurn().equals(whoseTurn()@prev)
	 */
	public void play(int pitChoice) {
		Player currentPlayer = this.currentPlayerObject.getValue();
		currentPlayer.takeTurn(pitChoice);

		this.determineIfGameIsOver();

		if (this.isGameOver) {
			this.determineWinner();
			this.currentPlayerObject.setValue(null);
		} else {
			this.swapWhoseTurn();
		}
	}

	private void determineIfGameIsOver() {
		int humanStoneCount = 0;
		for (int index = 0; index < this.theBoard.length / 2 - 1; index++) {
			humanStoneCount += this.theBoard[index];
		}

		int computerStoneCount = 0;
		for (int index = this.theBoard.length / 2; index < this.theBoard.length - 1; index++) {
			computerStoneCount += this.theBoard[index];
		}

		if (humanStoneCount == 0 || computerStoneCount == 0) {
			this.isGameOver = true;
		} else {
			this.isGameOver = false;
		}
	}

	private void determineWinner() {
		int humanStore = this.theBoard[this.theBoard.length / 2 - 1];
		int computerStore = this.theBoard[this.theBoard.length - 1];

		if (humanStore > computerStore) {
			this.theWinner = this.theHuman;
		} else if (humanStore < computerStore) {
			this.theWinner = this.theComputer;
		} else {
			this.theWinner = null;
		}
	}

	/**
	 * Returns a copy of the game board keeping track of the number of stones in
	 * each pit
	 * 
	 * @return The game board
	 */
	public int[] getGameBoard() {
		return this.theBoard.clone();
	}

	/**
	 * Sets up the board such that there is exactly 1 stone in each pit
	 */
	private void resetBoard() {
		for (int index = 0; index < this.theBoard.length / 2 - 1; index++) {
			this.theBoard[index] = this.startingStonesInPit;
			this.theBoard[index + this.theBoard.length / 2] = this.startingStonesInPit;
		}
	}

	private void swapWhoseTurn() {
		if (this.currentPlayerObject.getValue() == this.theComputer && !this.landedInMancala) {
			this.currentPlayerObject.setValue(this.theHuman);
		} else if (this.currentPlayerObject.getValue() == this.theHuman && !this.landedInMancala) {
			this.currentPlayerObject.setValue(this.theComputer);
		} else {
			Player temp = this.currentPlayerObject.getValue();
			this.currentPlayerObject.setValue(this.theComputer);
			this.currentPlayerObject.setValue(this.theHuman);
			this.currentPlayerObject.setValue(temp);
		}

	}

	/**
	 * Returns a String showing the current score, or, if the game is over, the name
	 * of the winner.
	 * 
	 * @return a String representation of this Game
	 */
	public String toString() {
		String result = this.theHuman.getName() + ": " + this.theBoard[this.theBoard.length / 2 - 1]
				+ System.getProperty("line.separator");
		result += this.theComputer.getName() + ": " + this.theBoard[this.theBoard.length - 1]
				+ System.getProperty("line.separator");
		if (this.isGameOver && this.theWinner != null) {
			result += this.theWinner.getName() + " wins";
		} else if (this.isGameOver && this.theWinner == null) {
			result += "Tie game";
		}

		return result;
	}

	@Override
	public void addListener(InvalidationListener theListener) {
		this.currentPlayerObject.addListener(theListener);
	}

	@Override
	public void removeListener(InvalidationListener theListener) {
		this.currentPlayerObject.removeListener(theListener);
	}

	/**
	 * Accessor for starting number of stones in each pit.
	 * 
	 * @return the startingStonesInPit
	 */
	public int getStartingStonesInPit() {
		return this.startingStonesInPit;
	}

	/**
	 * Mutator for starting number of stones in each pit
	 * 
	 * @param startingStonesInPit the startingStonesInPit to set
	 */
	public void setStartingStonesInPit(int startingStonesInPit) {
		this.startingStonesInPit = startingStonesInPit;
	}
}
