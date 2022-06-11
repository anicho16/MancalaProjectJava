/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;

/**
 * @author amber
 *
 */
class TestCreateGame {

	@Test
	public void testShouldProduceGameWithNoScore() {
		Game newGame = new Game();
		assertEquals("Human: 0" + System.getProperty("line.separator") + "Simple computer: 0", newGame.toString());
	}

}
