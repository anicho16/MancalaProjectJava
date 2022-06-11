/**
 * 
 */

package edu.westga.cs6910.mancala.test.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs6910.mancala.model.Game;
import edu.westga.cs6910.mancala.model.HumanPlayer;

/**
 * Test getGameBoard method in Game class
 * 
 * @author Amber Nicholas
 * @version 6.11.22
 *
 */
class TestGetGameBoard {
	private Game newGame = new Game();
	private HumanPlayer humanPlayer1 = new HumanPlayer("human", this.newGame);
	
	/**
	 * Test gameboard length is 8 after cloning gameboard
	 */
	@Test
	public void testGameBoardLengthIs8WhenCloned() {
		this.newGame.startNewGame(this.humanPlayer1);
		
		int[] clonedBoard = this.newGame.getGameBoard();
		
		assertEquals(8, clonedBoard.length);
	}

	/**
	 * Test getting number of stones is 1 when starting the game in first pit after cloning gameboard
	 */
	@Test
	public void testGetStonesFirstPitIsOneWhenStarting() {
		this.newGame.startNewGame(this.humanPlayer1);
		
		int[] clonedBoard = this.newGame.getGameBoard();
		
		assertEquals(1, clonedBoard[0]);
	}

}
