package com.simonhochrein.StrategicCommander.network.packets;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

public class SendShipsResponse implements Packet {
    public enum Status {
        Successful,
        Error
    }

    Status status;

    public SendShipsResponse() {}

    public SendShipsResponse(Status status) {
        this.status = status;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        this.status = buffer.readInt() == 1 ? Status.Error : Status.Successful;
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeInt(this.status == Status.Successful ? 0 : 1);
    }

    @Override
    public void handle(Connection connection) {
        System.out.println("Send Status "+this.status);
    }
}
