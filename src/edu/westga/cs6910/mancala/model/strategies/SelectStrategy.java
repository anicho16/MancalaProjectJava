/**
 * 
 */

package edu.westga.cs6910.mancala.model.strategies;

/**
 * Contains all game-play algorithms for the computer player to select a pit
 * 
 * @author Amber Nicholas
 * @version 6.18.22
 *
 */
public interface SelectStrategy {
	/**
	 * Defines which pit the computer player will choose
	 * @param pits - array of pits available on the game board
	 * @return int pit - selected pit
	 */
	int selectPit(int[] pits);
}
