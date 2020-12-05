package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class SendShipsPacket implements Packet {
    int fromId = 0;
    int toId = 0;
    int count = 0;

    public SendShipsPacket() {}

    public SendShipsPacket(int fromId, int toId, int count) {
        this.fromId = fromId;
        this.toId = toId;
        this.count = count;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        fromId = buffer.readInt();
        toId = buffer.readInt();
        count = buffer.readInt();
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeInt(fromId);
        buffer.writeInt(toId);
        buffer.writeInt(count);
    }

    @Override
    public void handle(Connection connection) {
        connection.send(new SendShipsResponse(SendShipsResponse.Status.Error));
    }
}
