package com.simonhochrein.StrategicCommander.server.gui;

import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.server.Chat;
import io.netty.channel.Channel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ServerUI extends JComponent {
    DefaultListModel<String> connections = new DefaultListModel<>();

    public ServerUI() {
        setPreferredSize(new Dimension(800, 800));
        setLayout(new BorderLayout());

        JList<String> connectionList = new JList<>();
        connectionList.setModel(connections);
        connectionList.setBorder(new TitledBorder("Player List"));

        JTextArea log = new JTextArea();
        log.setEditable(false);
        JScrollPane pane = new JScrollPane(log, 22, 30);
        pane.setBorder(new TitledBorder("Log"));

        Chat.getInstance().addListener((userId, message) -> {
            log.append("<"+userId+">: "+message+"\n");
        });

        add(pane, "Center");
        add(connectionList, "West");
    }

    public void addConnection(Connection connection) {
        connections.addElement(connection.getId().toString());
    }

    public void removeConnection(Connection connection) {
        connections.removeElement(connection.getId().toString());
    }
}

public class GUI {
    ServerUI ui;

    public void start() {
        JFrame frame = new JFrame();
        ui = new ServerUI();
        frame.add(ui);
        frame.pack();
        frame.setVisible(true);
    }

    public void addConnection(Connection connection) {
        ui.addConnection(connection);
    }

    public void removeConnection(Connection connection) {
        ui.removeConnection(connection);
    }
}
