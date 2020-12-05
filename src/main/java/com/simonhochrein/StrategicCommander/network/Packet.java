package com.simonhochrein.StrategicCommander.network;

import com.google.gson.JsonObject;
import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;

public interface Packet {

    void read(EnhancedBuffer buffer);
    void write(EnhancedBuffer buffer);

    void handle(Connection connection);
}
