package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class KeepAlivePingPacket implements Packet {
    long challenge;

    public KeepAlivePingPacket() {}

    public KeepAlivePingPacket(long challenge) {
        this.challenge = challenge;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        challenge = buffer.readLong();
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeLong(System.currentTimeMillis());
    }

    @Override
    public void handle(Connection connection) {
        connection.send(new KeepAlivePongPacket(challenge));
    }
}
