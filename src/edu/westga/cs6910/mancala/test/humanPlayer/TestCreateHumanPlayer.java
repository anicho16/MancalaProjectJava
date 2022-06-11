/**
 * 
 */

package edu.westga.cs6910.mancala.test.humanPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test constructor and getName method of HumanPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestCreateHumanPlayer {
	private Game newGame = new Game();

	/**
	 * Tests constructing human player and name should be string sent into constructor
	 */
	@Test
	void testCreateComputerPlayer() {
		HumanPlayer humanPlayer1 = new HumanPlayer("Amber", this.newGame);

		assertEquals("Amber", humanPlayer1.getName());
	}

}
