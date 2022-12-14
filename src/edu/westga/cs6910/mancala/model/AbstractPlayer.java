/**
 * 
 */

package edu.westga.cs6910.mancala.model;

/**
 * AbstractPlayer represents a player in the game Mancala.
 * 
 * @author Amber Nicholas
 * @version 6.5.22
 */
public abstract class AbstractPlayer implements Player {
	private String name;
	private boolean isMyTurn;

	private Game theGame;

	/**
	 * Creates a new AbstractPlayer with the specified name.
	 * 
	 * @param name    this Player's name
	 * @param theGame The Game that this player represents
	 * 
	 * @requires name != null
	 * @ensure name().equals(name) && getTotal() == 0
	 */
	public AbstractPlayer(String name, Game theGame) {
		if (theGame == null) {
			throw new IllegalArgumentException("Invalid Game object");
		}
		if (name == null) {
			throw new IllegalArgumentException("Invalid player name");
		}
		this.name = name;
		this.theGame = theGame;
	}

	@Override
	/**
	 * @see Player#getIsMyTurn()
	 */
	public boolean getIsMyTurn() {
		return this.isMyTurn;
	}

	@Override
	/**
	 * @see Player#getName()
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Used to set whether it is this players turn or not
	 * 
	 * @param isMyTurn Signifies whether it is this player's turn or not
	 */
	public void setIsMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}

	/**
	 * Accessor for Game being played
	 * 
	 * @return - the current Game
	 */
	public Game getGame() {
		return this.theGame;
	}

	@Override
	/**
	 * @see Player#takeTurn()
	 */
	public void takeTurn(int pitChoice) {
		while (this.theGame.getStones(pitChoice) == 0) {
			pitChoice--;
		}
		this.theGame.distributeStonesFrom(pitChoice);

		this.isMyTurn = false;
	}
}
