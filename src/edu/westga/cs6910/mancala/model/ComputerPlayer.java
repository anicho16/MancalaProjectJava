package edu.westga.cs6910.mancala.model;

/**
 * ComputerPlayer represents a very simple automated player in the game Mancala.
 * It always selects the closest pit for stones to be distributed
 * 
 * @author CS6910 Amber Nicholas
 * @version Summer 2022 6.5.22
 */
public class ComputerPlayer extends AbstractPlayer {
	private static final String NAME = "Simple computer";
	private int currentPit;

	/**
	 * Creates a new ComputerPlayer with the specified name.
	 * 
	 * @param theGame The Game that this player represents
	 * 
	 */
	public ComputerPlayer(Game theGame) {
		super(NAME, theGame);
		this.currentPit = 6;
	}

	/**
	 * Helps the computer player choose a pit with stones inside to move
	 * 
	 * @return - pit with stones on the computer's side of the board
	 */
	public int choosePit() {
		Game theGame = super.getGame();

		for (int iterator = theGame.getBoardSize() / 2; iterator < theGame.getBoardSize() - 1; iterator++) {
			if (theGame.getStones(iterator) != 0) {
				this.currentPit = iterator;
			}
		}
		return this.currentPit;
	}
}
