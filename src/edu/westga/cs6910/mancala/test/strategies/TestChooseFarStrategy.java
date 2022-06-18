/**
 * 
 */

package edu.westga.cs6910.mancala.test.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.strategies.FarStrategy;

/**
 * Test FarStrategy class causes the computer to select the pit farthest from
 * its goal.
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestChooseFarStrategy {

	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests that Computer chooses a pit with stones inside on its side of the board
	 * which is farthest from the goal. First choice should be pit number that is
	 * half the size of the board
	 */
	@Test
	void testChoosePitFarthesetFromGoal() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.newGame.getComputerPlayer().setStrategy(new FarStrategy());

		int result = this.newGame.getComputerPlayer().selectPit();

		int expectedValue = this.newGame.getBoardSize() / 2;

		assertEquals(expectedValue, result);
	}

}
