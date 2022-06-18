/**
 * 
 */
package edu.westga.cs6910.mancala.test.strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;

/**
 * Test NearStrategy class causes the computer to select the nearest pit.
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestChooseNearStrategy {

	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests Computer's chooses a pit with stones inside on its side of the board which is nearest to its goal.
	 * After computer takes first turn, pit 4 has no stones and pit 5 should be
	 * chosen as it would then be nearest to the computer's goal.
	 */
	@Test
	void testChoosePitClosestToGoal() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.newGame.play(this.computerPlayer1.selectPit());

		int result = this.computerPlayer1.selectPit();

		assertEquals(5, result);
	}

}
