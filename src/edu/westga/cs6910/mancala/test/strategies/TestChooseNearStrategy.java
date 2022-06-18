/**
 * 
 */

package edu.westga.cs6910.mancala.test.strategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.strategies.FarStrategy;
import edu.westga.cs6910.mancala.model.strategies.NearStrategy;

/**
 * Test NearStrategy class causes the computer to select the pit nearest to its
 * goal.
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestChooseNearStrategy {

	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests that Computer chooses a pit with stones inside on its side of the board
	 * which is nearest to its goal. First should should be number of pits minus 2
	 */
	@Test
	void testChoosePitClosestToGoal() {
		this.newGame.startNewGame(this.computerPlayer1);
		this.newGame.getComputerPlayer().setStrategy(new NearStrategy());

		int result = this.newGame.getComputerPlayer().selectPit();

		int expectedValue = this.newGame.getBoardSize() - 2;

		assertEquals(expectedValue, result);
	}

}
