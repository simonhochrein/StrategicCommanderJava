package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class ChatMessagePacket implements Packet {
    String userId;
    String message;

    public ChatMessagePacket(){}

    public ChatMessagePacket(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        userId = buffer.readString();
        message = buffer.readString();
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeString(userId);
        buffer.writeString(message);
    }

    @Override
    public void handle(Connection connection) {
        System.out.println("<"+userId+">: "+message);
    }
}
