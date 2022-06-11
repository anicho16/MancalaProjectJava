/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test getIsGameOver method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestGetIsGameOver {
	private Game newGame = new Game();
	private HumanPlayer firstPlayer = new HumanPlayer("human", this.newGame);

	/**
	 * Test game is not over before begins
	 */
	@Test
	public void testBeforeGameStartsIsNotOver() {
		assertEquals(false, this.newGame.getIsGameOver());
	}

	/**
	 * Test game is not over when first starts
	 */
	@Test
	public void testWhenGameFirstStartsIsNotOver() {
		this.newGame.startNewGame(this.firstPlayer);
		assertEquals(false, this.newGame.getIsGameOver());
	}

	/**
	 * Test game is over after a player has 0 stones on their side of the board
	 */
	@Test
	public void testGameIsOverWhenOneSideHasZeroStones() {
		this.newGame.startNewGame(this.firstPlayer);
		this.newGame.distributeStonesFrom(4);
		this.newGame.distributeStonesFrom(0);
		this.newGame.distributeStonesFrom(5);
		this.newGame.distributeStonesFrom(2);
		this.newGame.distributeStonesFrom(6);

		this.newGame.play(0);

		assertEquals(true, this.newGame.getIsGameOver());
	}

}
