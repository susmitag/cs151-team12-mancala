package edu.sjsu.cs.cs151.mancala.controller;

import edu.sjsu.cs.cs151.mancala.MancalaException;
import edu.sjsu.cs.cs151.mancala.controller.Valve;

public class ServerGameStateValve implements Valve {

    private ServerController controller;
    private Message responseMessage;

    public ServerGameStateValve (ServerController controller) {
        this.controller = controller;
    }

    public ValveResponse execute(Message m) {
        boolean isOver;
        if (m.getInfo().getGameEnded() && m.getInfo().isEarly())
            return ValveResponse.EXIT;
        try {
            responseMessage = controller.updateModel(m);
            isOver = m.getInfo().getGameEnded();
            // TODO write response to socket
        } catch (MancalaException e) {
            e.printStackTrace();
            //error dialog?
            return ValveResponse.MISS;
        }
        if (isOver)
            return ValveResponse.FINISHED;
        return ValveResponse.EXECUTED;
    }

    public Message getResponseMessage() {
        return responseMessage;
    }
}
