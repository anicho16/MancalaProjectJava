/**
 * 
 */

package edu.westga.cs6910.mancala.model.strategies;

/**
 * Contains algorithm for the computer player to select random pit with stones
 * 
 * @author Amber Nicholas
 * @version 6.18.22
 *
 */
public class RandomStrategy implements SelectStrategy {
	/**
	 * Chooses a pit on the computer's side that contains stones
	 * 
	 * @param pits - array of pits that may contain stones
	 * @return selectedPit - pit with stones
	 */
	public int selectPit(int[] pits) {
		int selectedPit = pits.length / 2;
		int max = pits.length - 2;
		int min = pits.length / 2;
		int range = max - min + 1;

		for (int iterator = pits.length - 2; iterator > pits.length / 2 - 1; iterator--) {
			selectedPit = (int) ((Math.random() * range) + min);
			while (pits[selectedPit] == 0) {
				selectedPit = (int) ((Math.random() * range) + min);
			}
		}
		return selectedPit;
	}
}
