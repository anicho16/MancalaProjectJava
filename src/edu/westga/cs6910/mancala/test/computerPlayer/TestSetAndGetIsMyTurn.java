/**
 * 
 */

package edu.westga.cs6910.mancala.test.computerPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test setMyTurn and getMyTurn methods of ComputerPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestSetAndGetIsMyTurn {
	private Game newGame = new Game();

	private ComputerPlayer npcPlayer = new ComputerPlayer(this.newGame);

	/**
	 * Tests set and get whether or not it is the npc's turn
	 */
	@Test
	void testGetAndSetIsMyTurn() {
		this.npcPlayer.setIsMyTurn(true);

		boolean result = this.npcPlayer.getIsMyTurn();

		assertEquals(true, result);
	}

}
