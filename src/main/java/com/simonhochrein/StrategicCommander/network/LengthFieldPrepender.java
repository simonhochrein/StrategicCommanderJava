package com.simonhochrein.StrategicCommander.network;

import com.simonhochrein.StrategicCommander.core.util.EnhancedBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LengthFieldPrepender extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf byteBuf2) throws Exception {
        EnhancedBuffer buf = new EnhancedBuffer(byteBuf2);
        buf.writeInt(byteBuf.readableBytes());
        buf.writeBytes(byteBuf.readerIndex(), byteBuf.readableBytes(), byteBuf);
    }
}
