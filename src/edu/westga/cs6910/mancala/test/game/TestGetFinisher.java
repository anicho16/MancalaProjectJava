/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test DistrubuteStonesFrom and Determining winner in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetFinisher {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);
	private ComputerPlayer aiPlayer = new ComputerPlayer(this.newGame);

	/**
	 * Test winner should be computer if more stones in store than human when game
	 * is over. Tests distributeStonesFrom and determining if game is over, and
	 * determining winner
	 */
	@Test
	public void testWinnerWhenComputerHasMoreStoresAtGameEnd() {
		this.newGame.startNewGame(this.aiPlayer);
		this.newGame.distributeStonesFrom(4);
		this.newGame.distributeStonesFrom(0);
		this.newGame.distributeStonesFrom(5);
		this.newGame.distributeStonesFrom(2);
		this.newGame.distributeStonesFrom(6);

		this.newGame.play(0);

		assertEquals("Human: 1" + System.getProperty("line.separator") + "Simple computer: 2"
				+ System.getProperty("line.separator") + "Simple computer wins", this.newGame.toString());
	}

	/**
	 * Test winner should be human if more stones in store than computer when game
	 * is over. Tests distributeStonesFrom and determining if game is over, and
	 * determining winner
	 */
	@Test
	public void testWinnerWhenHumanHasMoreInStoreAtGameEnd() {
		this.newGame.startNewGame(this.humanPlayer1);
		this.newGame.distributeStonesFrom(0);
		this.newGame.distributeStonesFrom(5);
		this.newGame.distributeStonesFrom(1);
		this.newGame.distributeStonesFrom(4);
		this.newGame.distributeStonesFrom(2);

		this.newGame.play(5);

		assertEquals("Human: 2" + System.getProperty("line.separator") + "Simple computer: 0"
				+ System.getProperty("line.separator") + "Human wins", this.newGame.toString());
	}

}
