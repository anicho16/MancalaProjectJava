package edu.westga.cs6910.mancala.model;

// TODO: Classes ComputerPlayer and HumanPlayer share most of their code.
//		 Refactor their code:
// 		 1. Create abstract base class AbstractPlayer to implement the
//			shared code and define abstract methods for methods without
//			duplicate code. AbstractPlayer should implement interface Player.
//		 2. Have ComputerPlayer and HumanPlayer extend AbstractPlayer to
//		    implement the unshared constructor code and the abstract methods.

/**
 * ComputerPlayer represents a very simple automated player in the game Mancala.
 * It always selects the closest pit for stones to be distributed
 * 
 * @author 	CS6910
 * @version Summer 2022
 */
public class ComputerPlayer implements Player {
	private static final String NAME = "Simple computer";
	
	private Game theGame;
	private String name;
	private boolean isMyTurn;
	
	/**
	 * Creates a new ComputerPlayer with the specified name.
	 * 
	 * @param	theGame	The Game that this player represents
	 * 
	 */
	public ComputerPlayer(Game theGame) {
		if (theGame == null) {
			throw new IllegalArgumentException("Invalid Game object");
		}
		
		this.theGame = theGame;
		this.name = NAME;
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

	@Override	
	/**
	 * @see Player#setIsMyTurn()
	 */
	public void setIsMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	
	@Override
	/**
	 * @see Player#takeTurn()
	 */	
	public void takeTurn(int pitChoice) {				
		pitChoice = this.theGame.getBoardSize() - 2;
		while (this.theGame.getStones(pitChoice) == 0) {
			pitChoice--;
		}
		this.theGame.distributeStonesFrom(pitChoice);

		this.isMyTurn = false;
	}
}
