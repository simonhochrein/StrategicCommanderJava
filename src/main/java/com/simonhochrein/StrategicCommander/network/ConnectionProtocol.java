package com.simonhochrein.StrategicCommander.network;

import com.simonhochrein.StrategicCommander.network.packets.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ConnectionProtocol {

    static PacketRegistrar packetRegistrar = new PacketRegistrar();

    static class PacketRegistrar {

        private final List<Supplier<? extends Packet>> idToConstructor;
        private final Map<Class<? extends Packet>, Integer> classToId;
        public PacketRegistrar() {
            idToConstructor = new ArrayList<>();
            classToId = new HashMap<>();
            registerPackets();
        }

        private void registerPackets() {
            registerPacket(MapPacket.class, MapPacket::new);
            registerPacket(SendShipsPacket.class, SendShipsPacket::new);
            registerPacket(SendShipsResponse.class, SendShipsResponse::new);
            registerPacket(KeepAlivePingPacket.class, KeepAlivePingPacket::new);
            registerPacket(KeepAlivePongPacket.class, KeepAlivePongPacket::new);
            registerPacket(ClientDisconnectPacket.class, ClientDisconnectPacket::new);

            registerPacket(ChatNewMessagePacket.class, ChatNewMessagePacket::new);
            registerPacket(ChatMessagePacket.class, ChatMessagePacket::new);
        }

        private <P extends Packet> PacketRegistrar registerPacket(Class<P> cls, Supplier<P> constructor) {
            if (classToId.get(cls) != null) {
                throw new RuntimeException("Failed to Register Packet");
            } else {
                classToId.put(cls, idToConstructor.size());
                idToConstructor.add(constructor);
            }
            return this;
        }

        public int idForClass(Class<?> packet) {
            return classToId.get(packet);
        }

        public Packet createPacket(int id) {
            Supplier packet = (Supplier) idToConstructor.get(id);
            return (Packet) packet.get();
        }
    }
}
