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
 * Test getStones method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetStones {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);
	private ComputerPlayer aiPlayer = new ComputerPlayer(this.newGame);

	/**
	 * Test getting number of stones is 1 when starting the game in first pit
	 */
	@Test
	public void testGetStonesFirstPitIsOneWhenStarting() {
		this.newGame.startNewGame(this.humanPlayer1);
		assertEquals(1, this.newGame.getStones(0));
	}

	/**
	 * Test getting number of stones is 0 when starting the game in human's mancala
	 */
	@Test
	public void testGetStonesHumanStoreIsZeroWhenStarting() {
		this.newGame.startNewGame(this.humanPlayer1);
		assertEquals(0, this.newGame.getStones(this.newGame.getBoardSize() / 2 - 1));
	}

	/**
	 * Test getting number of stones is 0 when starting the game in computer's
	 * mancala
	 */
	@Test
	public void testGetStonesComputerStoreIsZeroWhenStarting() {
		this.newGame.startNewGame(this.aiPlayer);
		assertEquals(0, this.newGame.getStones(this.newGame.getBoardSize() - 1));
	}

}
