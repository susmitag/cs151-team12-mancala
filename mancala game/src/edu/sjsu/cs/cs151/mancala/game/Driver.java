package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.view.*;

public class Driver {

	public static void main(String[] args) {
		Game game = Game.getGame();
		Board board = game.getBoard();
		Player player1 = new Player(board.getPlayer1Store());
		Player player2 = new Player(board.getPlayer2Store());
	}
}
