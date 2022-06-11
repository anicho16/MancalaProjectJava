/**
 * 
 */

package edu.westga.cs6910.mancala.test.humanPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.HumanPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test getGame method of HumanPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetGame {
	private Game newGame = new Game();

	private HumanPlayer humanPlayer1 = new HumanPlayer("Amber", this.newGame);

	/**
	 * Tests human's Game is the new game created and sent into the constructor
	 */
	@Test
	void testGetSizeOfGameHumanIsPlaying() {
		Game result = this.humanPlayer1.getGame();

		assertEquals(this.newGame.getBoardSize(), result.getBoardSize());
	}

}
