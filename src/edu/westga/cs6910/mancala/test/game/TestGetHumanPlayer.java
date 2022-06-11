/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test getHumanPlayer method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestGetHumanPlayer {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);

	/**
	 * Test that getHumanPlayer method results in "human"
	 */
	@Test
	public void testGetCurrentPlayerShouldBeHuman() {
		this.newGame.startNewGame(this.humanPlayer1);
		assertEquals("Human", this.newGame.getHumanPlayer().getName());
	}

}
