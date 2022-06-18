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
public class NearStrategy implements SelectStrategy {
	/**
	 * Always chooses the pit that is nearest to the goal that contains stones
	 * @param pits - array of pits that may contain stones
	 * @return selectedPit - pit nearest to the goal with stones
	 */
	public int selectPit(int[] pits) {
		int selectedPit = 4;
		
		for (int iterator = pits.length / 2; iterator < pits.length - 1; iterator++) {
			if (pits[iterator] != 0) {
				selectedPit = iterator;
			}
		}
		return selectedPit;
	}

}
