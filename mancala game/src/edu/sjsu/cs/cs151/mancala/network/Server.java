package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.controller.GameInfo;
import edu.sjsu.cs.cs151.mancala.controller.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable {
    private Socket accepted;
    private ObjectInputStream ois; //For receiving view user updates from client
    private ObjectOutputStream oos; //For sending model updates to client
    private LinkedBlockingQueue<Message> queue;
    private boolean done = false;

    public Server(LinkedBlockingQueue<Message> queue) {
        this.queue = queue;
    }

    public void run() {
        ServerSocket acceptSocket = null;
        try {
            acceptSocket = new ServerSocket(1111, 100, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            accepted = acceptSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(accepted.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(accepted.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!done) {
            GameInfo g;
            try {
                g = (GameInfo) ois.readObject();
                queue.add(new Message(g, true));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendResponseToClient (Message m) throws IOException {
        oos.writeObject(m.getInfo());
        oos.flush();
    }
}