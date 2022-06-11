/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;

/**
 * Test getIsGameOver method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetIsGameOver {

	@Test
	public void testBeforeGameStartsIsNotOver() {
		Game newGame = new Game();
		assertEquals(false, newGame.getIsGameOver());
	}

}
