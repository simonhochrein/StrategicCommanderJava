package com.simonhochrein.StrategicCommander.network;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import com.simonhochrein.StrategicCommander.network.packets.MapPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int type = byteBuf.readInt();
        Packet p = ConnectionProtocol.packetRegistrar.createPacket(type);
        p.read(new EnhancedBuffer(byteBuf));
        list.add(p);
    }
}
