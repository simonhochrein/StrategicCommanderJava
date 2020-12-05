package com.simonhochrein.StrategicCommander.network;
import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet o, ByteBuf byteBuf) throws Exception {
        EnhancedBuffer out = new EnhancedBuffer(byteBuf);
        out.writeInt(ConnectionProtocol.packetRegistrar.idForClass(o.getClass()));
        o.write(out);
    }
}
