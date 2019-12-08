package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.MancalaException;
import edu.sjsu.cs.cs151.mancala.model.Game;

import java.util.concurrent.LinkedBlockingQueue;

public class ServerController {
    private LinkedBlockingQueue<Message> queue;
    private Game model;

    public ServerController(LinkedBlockingQueue<Message> queue,  Game model) {
        this.queue = queue;
        this.model = model;
    }

    public Message updateModel(Message m) throws MancalaException {
        model.sow(m.getInfo().getChosenHole());
        Message m2 = model.getGameState();
        return m2;
    }
}
