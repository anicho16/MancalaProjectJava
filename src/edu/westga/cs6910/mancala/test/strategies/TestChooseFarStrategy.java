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
 * Test NearStrategy class causes the computer to select the pit farthest from its goal.
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestChooseFarStrategy {

	private Game newGame = new Game();

	private ComputerPlayer computerPlayer1 = new ComputerPlayer(this.newGame);

	/**
	 * Tests Computer's chooses a pit with stones inside on its side of the board which is farthest from the goal.
	 * After computer takes its turn twice, pit 6 should be the only pit left with stones.
	 */
	@Test
	void testChoosePitFarthesetFromGoal() {
		this.newGame.getComputerPlayer().setStrategy(new FarStrategy());
		this.newGame.startNewGame(this.computerPlayer1);
		this.newGame.play(this.computerPlayer1.selectPit());
		this.newGame.play(this.computerPlayer1.selectPit());

		int result = this.computerPlayer1.selectPit();

		assertEquals(6, result);
	}

}
