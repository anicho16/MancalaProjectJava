/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test setStartingStonesInPit method of Game class
 * 
 * @author Amber Nicholas
 * @version 6.26.22
 *
 */
class TestSetStartingStones {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);

	/**
	 * Test getting number of stones in initial pit after setting manually
	 */
	@Test
	public void testSetInitialStonesInPit() {
		this.newGame.setStartingStonesInPit(4);
		this.newGame.startNewGame(this.humanPlayer1);

		assertEquals(4, this.newGame.getStones(0));
	}

}
