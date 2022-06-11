/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test getComputerPlayer method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestGetComputerPlayer {
	private Game newGame = new Game();
	private ComputerPlayer aiPlayer = new ComputerPlayer(this.newGame);
	
	/**
	 * Test getting computer player results in "simple computer"
	 */
	@Test
	public void testGetComputerPlayer() {
		this.newGame.startNewGame(this.aiPlayer);
		assertEquals("simple computer", this.newGame.getComputerPlayer().getName());
	}

}
