/**
 * 
 */

package edu.westga.cs6910.mancala.test.computerPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test constructor and getName method of ComputerPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class CreateComputerPlayer {
	private Game newGame = new Game();

	/**
	 * Tests constructing computer player and name should be simple computer
	 */
	@Test
	void testCreateComputerPlayer() {
		ComputerPlayer aiPlayer = new ComputerPlayer(this.newGame);

		assertEquals("Simple computer", aiPlayer.getName());
	}

}
