package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class ClientDisconnectPacket implements Packet {
    @Override
    public void read(EnhancedBuffer buffer) {

    }

    @Override
    public void write(EnhancedBuffer buffer) {

    }

    @Override
    public void handle(Connection connection) {
        connection.disconnect();
    }
}
