package edu.sjsu.cs.cs151.mancala.game;

import edu.sjsu.cs.cs151.mancala.controller.*;
import edu.sjsu.cs.cs151.mancala.model.Game;
import edu.sjsu.cs.cs151.mancala.view.PlayScreen;
import edu.sjsu.cs.cs151.mancala.view.introAnimation.IntroAnimation;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerDriver {
    static LinkedBlockingQueue<Message> receiveQueue = new LinkedBlockingQueue<Message>();
    static class ServerListener extends Thread {
        public static void main(String... args) throws IOException {
            ServerListener server = new ServerListener();
            server.start();

            Socket socketToServer = new Socket("localhost", 15000);
            ObjectOutputStream outStream = new ObjectOutputStream(socketToServer.getOutputStream());

            Game model = Game.getGame();
            ServerController controller = new ServerController(queue, model);
            ServerGameStateValve gameValve = new ServerGameStateValve(controller);
            ValveResponse response = ValveResponse.EXECUTED;
            while (response != ValveResponse.FINISHED && response != ValveResponse.EXIT) {
                try {
                    Message m = receiveQueue.take();
                    response = gameValve.execute(m);
                    Message responseMessage = gameValve.getResponseMessage();
                    sendQueue.put(responseMessage);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (response != ValveResponse.EXIT)
                view.displayWinner(model.getWinnerIndex());
        }

        private ServerSocket serverSocket;

        ServerListener() throws IOException {
            serverSocket = ServerSocketFactory.getDefault().createServerSocket(15000);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Socket socketToClient = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(socketToClient);
                    clientHandler.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ClientHandler extends Thread{
        private Socket socket;
        ObjectInputStream inputStream;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Object o = inputStream.readObject();
                    System.out.println("Read object: "+o);
                    receiveQueue.put((Message)o);
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
