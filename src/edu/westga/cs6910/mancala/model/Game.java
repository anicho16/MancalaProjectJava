package edu.westga.cs6910.mancala.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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

	private Player theWinner;
	private boolean isGameOver;

	/**
	 * Creates a Mancala Game with the specified Players
	 * 
	 */
	public Game() {
		this.theHuman = new HumanPlayer("Human", this);
		this.theComputer = new ComputerPlayer(this);

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
	 * time in counter-clockwise order
	 * 
	 * @param pitNumber The pit number where the stones are to be taken
	 */
	public void distributeStonesFrom(int pitNumber) {
		if (pitNumber < 0) {
			throw new IllegalArgumentException("Pit number cannot be negative");
		}

		Player currentPlayer = this.currentPlayerObject.getValue();
		int stonesLeft = 0;

		if (currentPlayer.equals(this.theHuman)) {
			stonesLeft = this.distributeHumanStones(pitNumber, this.theBoard[pitNumber]);

			while (stonesLeft > 0) {
				stonesLeft = this.distributeHumanStones(pitNumber, stonesLeft);
			}
		} else {
			stonesLeft = this.distributeComputerStones(pitNumber, this.theBoard[pitNumber]);

			while (stonesLeft > 0) {
				stonesLeft = this.distributeComputerStones(pitNumber, stonesLeft);
			}
		}

		this.theBoard[pitNumber] = 0;
	}

	private int distributeHumanStones(int pitNumber, int stonesInPit) {
		int pitIncrease = 1;
		int wrapper = pitNumber;

		if (wrapper < this.theBoard.length - 2) {
			while (wrapper < this.theBoard.length - 2 && stonesInPit > 0) {
				wrapper++;
				this.theBoard[pitNumber + pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
			}
		}

		pitIncrease = 0;

		if (wrapper == this.theBoard.length - 2 && stonesInPit > 0) {
			while (wrapper > 0 && stonesInPit > 0) {
				this.theBoard[pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
				wrapper--;
			}
		}

		return stonesInPit;
	}

	private int distributeComputerStones(int pitNumber, int stonesInPit) {
		int pitIncrease = 1;
		int wrapper = pitNumber;

		if (wrapper < this.theBoard.length - 1) {
			while (wrapper < this.theBoard.length - 1 && stonesInPit > 0) {
				wrapper++;
				this.theBoard[pitNumber + pitIncrease] += 1;
				pitIncrease++;
				stonesInPit--;
			}
		}

		pitIncrease = 0;
		wrapper = 0;

		while (wrapper < this.theBoard.length / 2 - 1 && stonesInPit > 0) {
			this.theBoard[pitIncrease] += 1;
			pitIncrease++;
			stonesInPit--;
			wrapper++;
		}

		pitIncrease++;

		while (wrapper > 0 && stonesInPit > 0) {
			this.theBoard[pitIncrease] += 1;
			pitIncrease++;
			stonesInPit--;
			wrapper--;
		}

		return stonesInPit;
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
			this.theBoard[index] = 1;
			this.theBoard[index + this.theBoard.length / 2] = 1;
		}
	}

	private void swapWhoseTurn() {
		if (this.currentPlayerObject.getValue() == this.theComputer) {
			this.currentPlayerObject.setValue(this.theHuman);
		} else {
			this.currentPlayerObject.setValue(this.theComputer);
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
}
