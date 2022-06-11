/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.ComputerPlayer;
import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test getCurrentPlayer method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
public class TestGetCurrentPlayer {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);
	private ComputerPlayer aiPlayer = new ComputerPlayer(this.newGame);

	/**
	 * Test that current player when starting game as a human
	 */
	@Test
	public void testGetCurrentPlayerShouldBeHuman() {
		this.newGame.startNewGame(this.humanPlayer1);
		assertEquals("human", this.newGame.getCurrentPlayer().getName());
	}

	/**
	 * Test that current player when starting game with first player being computer
	 */
	@Test
	public void testGetCurrentPlayerShouldBeComputer() {
		this.newGame.startNewGame(this.aiPlayer);
		assertEquals("Simple computer", this.newGame.getCurrentPlayer().getName());
	}

	/**
	 * Test that current player is computer when players swap turns after human
	 * plays
	 */
	@Test
	public void testGetCurrentPlayerShouldBeComputerWhenSwapped() {
		this.newGame.startNewGame(this.humanPlayer1);
		this.newGame.play(0);
		assertEquals("Simple computer", this.newGame.getCurrentPlayer().getName());
	}

}
