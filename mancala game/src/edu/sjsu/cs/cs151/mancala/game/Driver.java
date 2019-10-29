package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.game.*;
import edu.sjsu.cs.cs151.mancala.view.*;

import java.util.Scanner;

public class Driver {
	/**
	 * Checks who is the current player and displays a message.
	 * @param p is the current player
	 * @param p1 is player1
	 * @param s is the message to display
	 */
	private static void displayForPlayer(Player p, Player p1, String s){
		if(p.equals(p1))
			System.out.println(s + " 1");
		else
			System.out.println(s + " 2");
	}

	public static void main(String[] args) {
		Game game = Game.getGame();
		Board board = game.getBoard();
		Player player1 = new Player(board.getPlayer1Store());
		Player player2 = new Player(board.getPlayer2Store());
		Player playerWithTurn = player1;
		while(true){
			System.out.println("State of the board (stores shown as []) : ");
			board.displayBoard();
			displayForPlayer(playerWithTurn, player1,"Turn for Player");
			Scanner sc = new Scanner(System.in);
			int index = sc.nextInt();
			Hole h = board.getHoleAt(index);
			boolean haveFreeTurn = playerWithTurn.selectHoleToSow(h);
			System.out.println("State of the board after sow (stores shown as []) : ");
			board.displayBoard();
			if(playerWithTurn.winner()){
				displayForPlayer(playerWithTurn, player1, "Winner is Player");
				break;
			}
			if(!haveFreeTurn){
				if(playerWithTurn.equals(player1))
					playerWithTurn = player2;
				else
					playerWithTurn = player1;
			}else{
				displayForPlayer(playerWithTurn, player1, "Free turn for Player");
			}
		}
	}
}