package edu.sjsu.cs.cs151.mancala.controller;
import java.net.ServerSocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import edu.sjsu.cs.cs151.mancala.model.*;

import edu.sjsu.cs.cs151.mancala.model.*;

public class MancalaServer {
    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
        try (ServerSocket listener = new ServerSocket(58901)) {
            System.out.println("Tic Tac Toe Server is Running...");
            ExecutorService pool = Executors.newFixedThreadPool(200);
            Game model = Game.getGame();
            while (true) {
                pool.execute(model.new Player(listener.accept()));
                pool.execute(model.new Player(listener.accept()));
            }
        }
    }
}
