package edu.sjsu.cs.cs151.mancala.networkTester;

import edu.sjsu.cs.cs151.mancala.view.*;
import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.game.*;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class Client{
    private static final String DEFAULT_IP = "localhost";
    private static final int DEFAULT_PORT = 7777;

    private final String ip = DEFAULT_IP;
    private final int port = DEFAULT_PORT;
    private final Scanner sc = new Scanner(System.in);

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean done = false;
    private Client clientSideConnection;
    private int playerID;
    private int otherPlayer;

    public Client(){

        System.out.println("____Client____");
        try {
            socket = new Socket("localhost", 51734);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("Connected to server as Player # "+ playerID);
            
        } 
        catch (final IOException e) {
            System.out.println("Client-side IOException");
        }
    }

    public void connectToServer(){
        clientSideConnection = new Client();
    }

    public void close(){
        try {
			in.close();
			out.close();
			socket.close();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
		done = true;
    }

    public static void main(final String[] args) throws IOException{
        final Driver game = new Driver();
        System.out.println("Connecting...");
        game.connectToServer();
        System.out.println("Connect successful");
    }
    
    public String getPlayerName(){
        System.out.println("Please enter a player name: ");
        final String playerName = sc.nextLine();
        return playerName;
    }
}