package edu.sjsu.cs.cs151.mancala.network;

import edu.sjsu.cs.cs151.mancala.controller.GameInfo;
import edu.sjsu.cs.cs151.mancala.controller.Message;
import edu.sjsu.cs.cs151.mancala.view.PlayScreen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Client implements Runnable {
    private boolean done = false;
    private LinkedBlockingQueue<Message> queue;
    private ObjectInputStream ois; //For receiving view updates from server
    private ObjectOutputStream oos; //For sending view changes from user to server
    private Socket connectSocket;
    private PlayScreen view;

    public Client(PlayScreen view, LinkedBlockingQueue<Message> queue, String host, int port) throws IOException {
        this.view = view;
        this.queue = queue;
        connectSocket = new Socket(host, port);
        oos = new ObjectOutputStream(connectSocket.getOutputStream());
        ois = new ObjectInputStream(connectSocket.getInputStream());
    }

    public void run() {
        while (!done) {
            GameInfo g = null;
            try {
                g = (GameInfo) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            queue.add(new Message(g, false));
        }
    }
}
