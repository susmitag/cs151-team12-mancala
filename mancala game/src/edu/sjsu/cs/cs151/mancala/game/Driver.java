package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.game.*;
import edu.sjsu.cs.cs151.mancala.view.*;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Game game = Game.getGame();
		Board board = game.getBoard();
		Player player1 = new Player(board.getPlayer1Store());
		Player player2 = new Player(board.getPlayer2Store());
		Player playerWithTurn = player1;
		while(true){
			Scanner sc = new Scanner(System.in);
			int index = sc.nextInt();
			Hole h = board.getHoleAt(index);
			boolean haveFreeTurn = playerWithTurn.selectHoleToSow(h);
			if(playerWithTurn.winner()){
				break;
			}
			if(!haveFreeTurn){
				if(playerWithTurn.equals(player1))
					playerWithTurn = player2;
				else
					playerWithTurn = player1;
			}
		}
	}
}