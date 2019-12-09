package edu.sjsu.cs.cs151.mancala.controller;

public class GameConfig {
    private int mode;
    private int port;
    private String hostname;

    public GameConfig(int mode) {
        this.mode = mode;
    }

    public GameConfig(int mode, String hostname, int port) {
        this.mode = mode;
        this.hostname = hostname;
        this.port = port;
    }

    public int getMode() {
        return mode;
    }

    public String getHostname () {
        return hostname;
    }

    public int getPort () {
        return port;
    }
}
