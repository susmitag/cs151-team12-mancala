package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.model.*;
import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;

import java.util.concurrent.*;

public class Driver {

	public static void main(String[] args) 
	{
		LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<Message>(); 
		PlayScreen view = PlayScreen.init(queue);
		Game model = Game.getGame();
		Controller controller = new Controller(queue, view, model);
		UpdateGameStateValve gameValve = new UpdateGameStateValve(controller);
		ValveResponse response = ValveResponse.EXECUTED;
		while (response != ValveResponse.FINISHED) {
			try {
				Message m = queue.take();
				response = gameValve.execute(m);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		view.close();
		queue.clear();
	}		
}
	
	


	/**
	 * Checks who is the current player and displays a message.
	 * @param p is the current player
	 * @param p1 is player1
	 * @param s is the message to display
	 */
	/*
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
		Scanner sc = new Scanner(System.in);
		while(!game.gameStatus()){
			System.out.println("State of the board (stores shown as []) : ");
			board.displayBoard();
			displayForPlayer(playerWithTurn, player1,"Turn for Player");
			int index = sc.nextInt();
			Hole h = board.getHoleAt(index);
			try {
				boolean haveFreeTurn = playerWithTurn.selectHoleToSow(h);
				System.out.println("State of the board after sow (stores shown as []) : ");
				board.displayBoard();
				if(!haveFreeTurn){
					if(playerWithTurn.equals(player1))
						playerWithTurn = player2;
					else
						playerWithTurn = player1;
				}else{
					displayForPlayer(playerWithTurn, player1, "Free turn for Player");
				}
			}
			catch (MancalaException msg) {
				System.out.println(msg);
			}
		}
		if (player1.winner())
			displayForPlayer(player1, player1, "Winner is Player");
		else if (player2.winner())
			displayForPlayer(player2, player1, "Winner is Player");
		else
			System.out.println("It's a tie!");
		sc.close();
	}
}
*/
