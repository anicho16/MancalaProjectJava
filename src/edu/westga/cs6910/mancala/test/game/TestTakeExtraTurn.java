package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test DistrubuteStonesFrom and Determining winner in Game class
 * 
 * @author Amber Nicholas
 * @version 6.25.22
 *
 */
public class TestTakeExtraTurn {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);

	/**
	 * Test current player is still the human after human takes a turn and lands in own
	 * Mancala
	 */
	@Test
	public void testWinnerWhenComputerHasMoreStoresAtGameEnd() {
		this.newGame.startNewGame(this.humanPlayer1);
		this.newGame.play(2);

		assertEquals(this.humanPlayer1, this.newGame.getCurrentPlayer());
	}

}
