package edu.sjsu.cs.cs151.mancala.model;

import edu.sjsu.cs.cs151.mancala.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A Game object represents a single mancala game between two players.
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Game {
	public boolean gameOver = false;

	private Board board = new Board();
	private static final Game instance = new Game();
	private int playerWithTurn = 0;
	private boolean turnChanged = true;

	/**
	 * Private constructor to prevent creation of multiple games
	 */
	private Game() {
		// instance fields already constructed, nothing to do here
	}
	
	/**
	 * Returns reference to the Game
	 * @return the Game object
	 */
	public static Game getGame() {
		return instance;
	}
	
	/**
	 * Resets board to starting format
	 */
	public void reset() {
		board = new Board();
	}
	
	/**
	 * Returns the index of the winning player's store
	 * @return index of store with the most marbles
	 */
	public int getWinnerIndex() {
		if (board.getPlayer1Store().getMarblecount() > board.getPlayer2Store().getMarblecount())
			return Board.PLAYER1_STORE_INDEX;
		else 
			return Board.PLAYER2_STORE_INDEX;
	}
	/**
	 * Gives a Message containing info about the number of marbles in each hole,
	 * whether the current player changed and the changed current player
	 * 	The index of the array corresponds with the index of the Hole
	 * @return Message with array of marbles counts
	 */
	public Message getGameState() {
		int[] marbleCounts = new int[Board.AMOUNT_OF_HOLES];
		boolean[] holeActiveState = new boolean[Board.AMOUNT_OF_HOLES];
		for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
			marbleCounts[i] = board.getHoleAt(i).getMarblecount();
            holeActiveState[i] = board.getHoleAt(i).getIsActive();
		}
		return new Message(new GameInfo(marbleCounts, holeActiveState, turnChanged, playerWithTurn, gameStatus()));
	}

	/**
	 * Given an index of a Hole, check if that belongs to player with turn
	 * @param index index of Hole to sow
	 * @return true if index belongs to player with turn
	 */
	private boolean isHoleValid(int index) {
		if (((playerWithTurn == 0) && (index >=0 && index <= 5)) ||
		   ((playerWithTurn == 1) && (index >=7 && index <= 12)))
			return true;
		return false;
	}

	/**
	 * Given an index of a Hole, sows that Hole
	 * @param index index of Hole to sow
	 */
	public void sow(int index) throws MancalaException {
		if (board.getHoleAt(index).getMarblecount() <= 0)
			return;
		if(isHoleValid(index)){
            for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
                board.getHoleAt(i).setIsActive(false);
            }
			Hole h = board.getHoleAt(index);
			Store s = board.getCorrespondingStore(index);
			boolean freeTurn = sow(h, s);
			if (s.equals(board.getPlayer1Store()))
				s = board.getPlayer2Store();
			else
				s = board.getPlayer1Store();
			checkCaptureRemaining(s);
			if(!freeTurn){
				playerWithTurn = (playerWithTurn + 1) % 2;
				turnChanged = true;
			} else {
				turnChanged = false;
			}
		}
	}
	
	/**
	 * Places all marbles in\
	 *  selected hole into the next holes and stores according to mancala's rules.
	 * 
	 * @param hole the hole to take the marbles from
	 * @param playerStore the Store corresponding to the hole being sown
	 * @return true if the last marble sowed ends in player's store (for free turn)
	 */
	public boolean sow(Hole hole, Store playerStore) throws MancalaException {
		if (Game.getGame().getBoard().checkIfStore(hole)) {
			throw new MancalaException("Error: can not sow marbles from a store.");
		}
		int marbleCount = hole.removeMarbles();
		hole.setIsActive(true);
		Board board = Game.getGame().getBoard();
		while (marbleCount > 0) {
			hole = board.getNextHole(hole);
			// add marbles to the hole only if it is the calling player's store, or if it is a hole
			if ((board.checkIfStore(hole) && hole.equals(playerStore)) || !board.checkIfStore(hole)) {
					hole.addMarble();
                    hole.setIsActive(true);
					marbleCount--;
			}
		}
		//checks if last marble placed was in their store (player goes again)
		if (hole.equals(playerStore)) 
			return true;
		else {
			if (hole.getMarblecount() == 1) { 				// if the hole has 1 marble, then it was previously empty and
				board.captureHole(hole, playerStore, true);   //    the player captures all marbles in the opposite hole
			}
			return false;
		}
	}

	/**
	 * Check if the game is over, and if so give the rest of the marbles to store
	 * @param s Store to add marbles to
	 */
	private void checkCaptureRemaining (Store s) {
		if (gameStatus()) {
			for (int i = 0; i < Board.AMOUNT_OF_HOLES; i++) {
				if (!board.checkIfStore(board.getHoleAt(i)))
					board.captureHole(board.getHoleAt(i), s, false);
			}
		}
	}

	/**
	 * Accessor method for the Game's board
	 * @return the current Game Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Checks if all holes on one side is empty
	 * @return returns a boolean value that's true if all holes on one side is empty
	 */
	private boolean checkSideEmpty(int start, int end) {
		boolean isSideEmpty = true;
		for(int i=start; i < end; i++){
			Hole h = board.getHoleAt(i);
			if(board.checkIfStore(h)) continue;
			if(h.getMarblecount() > 0) {
				isSideEmpty = false;
				break;
			}
		}
		return isSideEmpty;
	}

	/**
	 * Checks for the current game's running status
	 * @return returns a boolean value that's true if the game is over, false if the game is still going
	 */
	public boolean gameStatus() {
		int numberOfHoles = Board.AMOUNT_OF_HOLES;
		return (checkSideEmpty(0, numberOfHoles/2 - 1)) || (checkSideEmpty(numberOfHoles/2, numberOfHoles));
	}

	public class Player implements Runnable {
		Socket socket;
		ObjectInputStream ois;

		public Player(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(socket.	getInputStream());
		}

		public void run() {
			try {
				Message m;
				while ((m = (Message) ois.readObject()) != null) {
					queue.add(m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}

			}
		} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}