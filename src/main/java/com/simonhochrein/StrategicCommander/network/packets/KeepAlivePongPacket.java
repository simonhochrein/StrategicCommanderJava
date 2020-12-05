package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class KeepAlivePongPacket implements Packet {
    long challenge;
    public KeepAlivePongPacket() {}

    public KeepAlivePongPacket(long challenge) {
        this.challenge = challenge;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        this.challenge = buffer.readLong();
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeLong(challenge);
    }

    @Override
    public void handle(Connection connection) {
        connection.keepAlivePending = false;
    }
}
