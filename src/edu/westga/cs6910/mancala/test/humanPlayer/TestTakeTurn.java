/**
 * 
 */

package edu.westga.cs6910.mancala.test.humanPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test takeTurn method of HumanPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestTakeTurn {
	private Game newGame = new Game();

	private HumanPlayer humanPlayer1 = new HumanPlayer("Amber", this.newGame);

	/**
	 * Tests human's pit is empty after taking a turn on that pit
	 */
	@Test
	void testPitIsEmptyAfterTakeTurn() {
		this.newGame.startNewGame(this.humanPlayer1);
		this.humanPlayer1.takeTurn(0);

		int result = this.newGame.getStones(0);

		assertEquals(0, result);
	}

	/**
	 * Tests it is no longer the human's turn after taking a turn.
	 */
	@Test
	void testTakeTurnSwitchesPlayers() {
		this.newGame.startNewGame(this.humanPlayer1);
		this.humanPlayer1.takeTurn(0);

		boolean result = this.humanPlayer1.getIsMyTurn();

		assertEquals(false, result);
	}

}
