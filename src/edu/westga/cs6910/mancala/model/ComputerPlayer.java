package edu.westga.cs6910.mancala.model;

import edu.westga.cs6910.mancala.model.strategies.FarStrategy;
import edu.westga.cs6910.mancala.model.strategies.NearStrategy;
import edu.westga.cs6910.mancala.model.strategies.SelectStrategy;

/**
 * ComputerPlayer represents a very simple automated player in the game Mancala.
 * It always selects the closest pit for stones to be distributed
 * 
 * @author CS6910 Amber Nicholas
 * @version Summer 2022 6.5.22
 */
public class ComputerPlayer extends AbstractPlayer {
	private static final String NAME = "Simple computer";
	private SelectStrategy strategy;

	/**
	 * Creates a new ComputerPlayer with the specified name.
	 * 
	 * @param theGame The Game that this player represents
	 * 
	 */
	public ComputerPlayer(Game theGame) {
		super(NAME, theGame);
		this.strategy = new FarStrategy();
	}

	/**
	 * Sets the strategy the computer will use to choose a pit
	 * 
	 * @param strategy - strategy to use
	 */
	public void setStrategy(SelectStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Gets the strategy the computer is currently using to choose a pit
	 * 
	 * @return - strategy being used
	 */
	public SelectStrategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Helps the computer player choose a pit with stones inside to move
	 * 
	 * @return - pit with stones on the computer's side of the board
	 */
	public int selectPit() {
		return this.strategy.selectPit(super.getGame().getGameBoard());
	}
}
