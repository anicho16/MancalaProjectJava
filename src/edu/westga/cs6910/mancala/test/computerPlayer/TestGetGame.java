/**
 * 
 */

package edu.westga.cs6910.mancala.test.computerPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test getGame method of ComputerPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetGame {
	private Game newGame = new Game();

	private ComputerPlayer npcPlayer = new ComputerPlayer(this.newGame);

	/**
	 * Tests npc's Game is the new game created and sent into the constructor
	 */
	@Test
	void testGetSizeOfGameComputerIsPlaying() {
		Game result = this.npcPlayer.getGame();

		assertEquals(this.newGame.getBoardSize(), result.getBoardSize());
	}

}
