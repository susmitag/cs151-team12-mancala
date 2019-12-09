package edu.sjsu.cs.cs151.mancala.networkTester;

import java.applet.*;
import java.util.Scanner;

import javax.swing.JFrame;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.lang.Thread;


/**
 * Manages the connection between two players in the server
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Server{
    private final String DEFAULT_IP ="localhost";
    private final int DEFAULT_PORT = 51734;

    private String ip = DEFAULT_IP;
    private int port = DEFAULT_PORT;
    private Scanner sc = new Scanner(System.in);


    static ServerSocket serverSocket;

    private boolean done = false;
    private boolean running = true;
    private int numPlayers;

    private ServerSideConnection player1;
    private ServerSideConnection player2;

    public Server(){
        System.out.println("Starting server...");
        numPlayers = 0;
        try {
            serverSocket = new ServerSocket(51734); 
        } 
        catch (IOException e) {
            System.out.println("IOException from Server()");
        }
        System.out.println("Server started...");
    }

    public void acceptConnection(){
        try {
            System.out.println("Waiting for connections...");
            while (numPlayers < 2){
                Socket s = serverSocket.accept();
                numPlayers++;
                System.out.println("Player # "+ numPlayers + " have connected");
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);
                if(numPlayers == 1){
                    player1 = ssc;
                }
                else{
                    player2 = ssc;
                }
                Thread t = new Thread(ssc);
                t.start();
            }
        } 
        catch (IOException e) {
            System.out.println("IOException from acceptConnection()");
        }
    }

    private class ServerSideConnection implements Runnable{
        private Socket socket;
        private DataOutputStream out;
        private DataInputStream in;
        private int playerID;

        public ServerSideConnection(Socket s, int id){
            socket = s;
            playerID = id;
            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException e) {
                System.out.println("IOException from ServerSideConnection()");
            }
        }

        @Override
        public void run(){
            try {
                out.writeInt(playerID);
                out.flush();

                while(running){

                }
            } 
            catch (IOException e) {
                System.out.println("IOException from run() ServerSideConnection()");
            }
        }
    }

    public static void main(String[] args)throws Exception{
        Server server = new Server();
        server.acceptConnection();
    }

    public void setHost(String ip) {
		this.ip = ip;
	}
	
	public void setPort(int port) {
		this.port = port;
    }
}
    
    

    



