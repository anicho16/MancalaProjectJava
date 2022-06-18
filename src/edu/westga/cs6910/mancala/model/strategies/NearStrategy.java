/**
 * 
 */

package edu.westga.cs6910.mancala.model.strategies;

/**
 * Contains algorithm for the computer player to select the pit nearest to its
 * goal
 * 
 * @author Amber Nicholas
 * @version 6.18.22
 *
 */
public class NearStrategy implements SelectStrategy {
	/**
	 * Always chooses the pit that is nearest to the goal that contains stones
	 * 
	 * @param pits - array of pits that may contain stones
	 * @return selectedPit - pit nearest to the goal with stones
	 */
	public int selectPit(int[] pits) {
		int selectedPit = pits.length / 2;

		for (int iterator = pits.length / 2; iterator < pits.length - 1; iterator++) {
			if (pits[iterator] != 0) {
				selectedPit = iterator;
			}
		}
		return selectedPit;
	}

}
