package edu.westga.cs6910.mancala.model;

/**
 * HumanPlayer represents a human player in the game Mancala.
 * 
 * @author 	CS6910
 * @version Summer 2022
 */
public class HumanPlayer implements Player {
	private String name;
	private boolean isMyTurn;	
	
	private Game theGame;
	
	/**
	 * Creates a new ComputerPlayer with the specified name.
	 * 
	 * @param 	name	this Player's name
	 * @param	theGame	The Game that this player represents
	 * 
	 * @requires	name != null
	 * @ensure		name().equals(name) && getTotal() == 0
	 */
	public HumanPlayer(String name, Game theGame) {
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
	 * @param	isMyTurn	Signifies whether it is this player's 
	 * 						turn or not
	 */
	public void setIsMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
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
