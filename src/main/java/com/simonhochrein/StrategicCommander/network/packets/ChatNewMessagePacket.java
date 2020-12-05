package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;
import com.simonhochrein.StrategicCommander.server.Chat;

public class ChatNewMessagePacket implements Packet {
    String message;

    public ChatNewMessagePacket(){}

    public ChatNewMessagePacket(String message) {
        this.message = message;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        message = buffer.readString();
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeString(message);
    }

    @Override
    public void handle(Connection connection) {
        Chat.getInstance().broadcast(connection.getId().toString(), message);
    }
}
