package edu.sjsu.cs.cs151.mancala;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client{
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    public static void main(String[] args) throws IOException{
        System.out.println("Connecting...");
        socket = new Socket("localhost",7777);

        System.out.println("Connect successful");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        
        Input input = new Input(in);
        Thread thread = new Thread(input);
        thread.start();
        Scanner sc = new Scanner(System.in);
        while(true){
            String str = sc.nextLine();
            out.writeUTF(str);

        }
        

        //System.out.println("Recieving information...");
        //String test = in.readUTF();

        //System.out.println("Message from Connection: " + test);
    }
    
    
}
class Input implements Runnable{
    public Input(DataInputStream in){
        this.in = in;
    }
    public void run(){
        while(true){
            String message;
            try{    
                message = in.readUTF();
                for(int i = 0; i<2; i++){
                    if(user[i] != null){
                        user[i].out.writeUTF(message);
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}