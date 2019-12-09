package edu.sjsu.cs.cs151.mancala;

import java.applet.*;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.lang.Thread;


/**
 * Manages the connection between two players in the server
 * Currently incomplete due to GUI and network problems needed to solve first
 */
public class Server implements Runnable{
    private final String DEFAULT_IP ="localhost";
    private final int DEFAULT_PORT = 22222;

    private String ip = DEFAULT_IP;
    private int port = DEFAULT_PORT
    private Scanner scanner = new Scanner(System.in);

    static Socket socket;
    static DataOutputStream out;
    static DataInputStream in;

    static ServerSocket serverSocket;

    private boolean done = false;

    public static void main(String[] args)throws Exception{
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(7777); 
        System.out.println("Server started...");
        
        while(true){
            socket = serverSocket.accept();

            for(int i = 0; i < 2; i++){
                System.out.println("Connection from "+ socket.getInetAddress());
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
               if(user[i] == null){
                    user[i] = new Users(out,in,user);
                    Thread thread = new Thread(user[i]);
                    thread.start();
                    break;
                }
                //out.writeUTF("This is a test of java socket");
                //System.out.println("Data Sent");
            }  
        }
    }

    static Users[] user = new Users[2];
    class Users implements Runnable{
        DataOutputStream out;
        DataInputStream in;
        Users[] user = new Users[2];
    
        public Users(DataOutputStream out, DataInputStream in, Users[] user){
            this.out = out;
            this.in = in;
            this.user = user;
    
        }
        public void run(){
            while (true){
                
            }
        }
    }
    @Override
    public void run() {
        while(true){
            try{    
                String message = in.readUTF();
                for(int i = 0; i<2; i++){
                    if(user[i] != null){
                        user[i].out.writeUTF(message);
                    }
                }
            }
            catch(IOException e){
                this.out = null;
                this.in = null;
            }
        }
    }
    public Server() throws IOException {
        
        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        int ipAddress = Integer.parseInt(ip);
        System.out.println("Please input the port");
        port = scanner.nextInt();
        while(port < 1 | port > 65535){
            System.out.println("Please input a valid port");
            port = scanner.nextInt();
        }
    }
    public void close() {
		try {
			in.close();
			out.close();
            socket.close();
            serverSocket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		done = true;
	}
	
    public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
    
    

    



