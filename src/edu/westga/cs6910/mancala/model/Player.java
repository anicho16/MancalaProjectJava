package edu.westga.cs6910.mancala.model;

/**
 * Player defines the interface for players in the game Mancala.
 * 
 * @author 	CS6910
 * @version Summer 2022
 */
public interface Player {
	/**
	 * Returns whether or not it is this player's turn
	 * @return	True if and only if it is this player's turn
	 */
	boolean getIsMyTurn();
	
	/**
	 * Returns this Player's name.
	 * 
	 * @return the name
	 */
	String getName();
	
	/**
	 * Used to set whether it is this players turn or not
	 * @param	isMyTurn	Signifies whether it is this player's 
	 * 						turn or not
	 */
	void setIsMyTurn(boolean isMyTurn);
	
	/**
	 * Describes the actions for this player to take its turn
	 * 
	 * @param	pitChoice	The pit number where the stones will
	 * 						be taken from
	 * 
	 */
	void takeTurn(int pitChoice);
}
