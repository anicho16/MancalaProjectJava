/**
 * 
 */

package edu.westga.cs6910.mancala.test.humanPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test setMyTurn and getMyTurn methods of HumanPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestSetAndGetIsMyTurn {
	private Game newGame = new Game();

	private HumanPlayer humanPlayer1 = new HumanPlayer("Amber", this.newGame);

	/**
	 * Tests set and get whether or not it is the human's turn
	 */
	@Test
	void testCreateComputerPlayer() {
		this.humanPlayer1.setIsMyTurn(false);

		boolean result = this.humanPlayer1.getIsMyTurn();

		assertEquals(result, this.humanPlayer1.getIsMyTurn());
	}

}
