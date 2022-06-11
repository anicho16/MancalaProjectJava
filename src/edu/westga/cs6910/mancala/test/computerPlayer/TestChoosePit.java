/**
 * 
 */

package edu.westga.cs6910.mancala.test.computerPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test choosePit method of ComputerPlayer class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestChoosePit {
	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests Computer's chooses a pit with stones inside on its side of the board.
	 * After computer takes first turn, pit 4 has no stones and pit 5 should be
	 * chosen.
	 */
	@Test
	void testPitIsEmptyAfterTakeTurn() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.newGame.play(this.computerPlayer1.choosePit());

		int result = this.computerPlayer1.choosePit();

		assertEquals(5, result);
	}

}
