package com.simonhochrein.StrategicCommander.network;

import com.simonhochrein.StrategicCommander.network.packets.MapPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class Connection extends SimpleChannelInboundHandler<Packet> {
    private UUID id = UUID.randomUUID();
    public boolean keepAlivePending;
    public long keepAliveLastRequest;
    public boolean disconnected = false;

    ConnectionProtocol protocol = new ConnectionProtocol();
    private Channel channel;

    public void disconnect() {
        channel.close().syncUninterruptibly();
    }

    public UUID getId() {
        return id;
    }

    public boolean isOpen() {
        return channel.isOpen();
    }

    public enum Role {
        SERVER,
        CLIENT
    }

    private Role role;

    public Connection(Role role) {
        this.role = role;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) {
        try {
            packet.handle(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        this.disconnected = true;
    }

    public void send(Packet packet) {
        if (this.channel.eventLoop().inEventLoop()) {
            this.channel.writeAndFlush(packet).syncUninterruptibly();
        } else {
            this.channel.eventLoop().execute(() -> {
                this.channel.writeAndFlush(packet).syncUninterruptibly();
            });
        }
    }
}
