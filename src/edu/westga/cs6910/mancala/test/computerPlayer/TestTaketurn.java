/**
 * 
 */

package edu.westga.cs6910.mancala.test.computerPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test takeTurn method of ComputerPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestTaketurn {
	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests Computer's pit is empty after taking a turn on that pit
	 */
	@Test
	void testPitIsEmptyAfterTakeTurn() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.computerPlayer1.takeTurn(6);

		int result = this.newGame.getStones(6);

		assertEquals(0, result);
	}

	/**
	 * Tests it is no longer the Computer's turn after taking a turn.
	 */
	@Test
	void testTakeTurnSwitchesPlayers() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.computerPlayer1.takeTurn(0);

		boolean result = this.computerPlayer1.getIsMyTurn();

		assertEquals(false, result);
	}

}
