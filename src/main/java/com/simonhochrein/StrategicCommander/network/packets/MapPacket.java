package com.simonhochrein.StrategicCommander.network.packets;

import com.google.gson.JsonObject;
import com.simonhochrein.StrategicCommander.client.multiplayer.Multiplayer;
import com.simonhochrein.StrategicCommander.core.Planet;
import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.Connection;
import com.simonhochrein.StrategicCommander.network.Packet;

import java.util.ArrayList;
import java.util.List;

public class MapPacket implements Packet {

    private List<Planet> planetList;

    public MapPacket() {
        planetList = new ArrayList<>();
    }

    public MapPacket(List<Planet> planets) {
        this.planetList = planets;
    }

    @Override
    public void read(EnhancedBuffer buffer) {
        int length = buffer.readInt();
        for(int i = 0; i < length; i++) {
            planetList.add(new Planet(buffer.readInt(), buffer.readVector2f(), buffer.readString(), buffer.readInt(), buffer.readBoolean()));
        }
    }

    @Override
    public void write(EnhancedBuffer buffer) {
        buffer.writeInt(planetList.size());
        for(Planet p : planetList) {
            buffer.writeInt(p.getId());
            buffer.writeVector2f(p.getPosition());
            buffer.writeString(p.getName());
            buffer.writeInt(p.getOwnerId());
            buffer.writeBoolean(p.isHomeworld());
        }
    }

    @Override
    public void handle(Connection connection) {
        Multiplayer.getInstance().planetList.clear();
        Multiplayer.getInstance().planetList.addAll(planetList);
    }
}
