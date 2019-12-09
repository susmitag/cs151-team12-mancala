package edu.sjsu.cs.cs151.mancala.view;

import edu.sjsu.cs.cs151.mancala.controller.GameConfig;

import javax.swing.*;

public class ConfigureDialog extends JOptionPane {
    public static final int NEW_GAME = 1;
    public static final int CONNECT_TO_GAME = 2;
    public static GameConfig dialog() {
        int mode = -1;
        Object[] locationOptions = {"local game", "network game"};
        int response = showOptionDialog(null, "Play locally or across network?",
                "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, locationOptions,
                null);
        if (response == 0) return new GameConfig(response);
        else {
            Object[] networkOptions = {"start new game", "connect to game"};
            response = showOptionDialog(null, "Start new game, or connect to game?",
                    "Setup", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, networkOptions,
                    null);
            if (response == 0) mode = NEW_GAME;
            else mode = CONNECT_TO_GAME;
        }

        JTextField hostnameField = new JTextField(20);
        JTextField portField = new JTextField(10);
        JLabel hostnameLabel = new JLabel("hostname:");
        JLabel portLabel = new JLabel("port:");
        JPanel panel = new JPanel();
        panel.add(hostnameLabel);
        panel.add(hostnameField);
        panel.add(portLabel);
        panel.add(portField);
        String hostname = "localhost";
        int port = 1111;
        JOptionPane.showConfirmDialog(null, panel, "Setup", JOptionPane.PLAIN_MESSAGE);
        hostname = hostnameField.getText().trim();
        port = Integer.parseInt(portField.getText().trim());
        return new GameConfig(mode, hostname, port);
    }
}