/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;

/**
 * Test getBoardSize method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetBoardSize {

	@Test
	public void testNewGameShouldHaveBoardSize8() {
		Game newGame = new Game();
		assertEquals(8, newGame.getBoardSize());
	}

}
