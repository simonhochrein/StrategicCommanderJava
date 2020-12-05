package com.simonhochrein.StrategicCommander.client.multiplayer;

import com.simonhochrein.StrategicCommander.core.Planet;
import com.simonhochrein.StrategicCommander.network.*;
import com.simonhochrein.StrategicCommander.network.packets.ClientDisconnectPacket;
import com.simonhochrein.StrategicCommander.network.packets.MapPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.ArrayList;
import java.util.List;

public class Multiplayer {
    public List<Planet> planetList = new ArrayList<>();
    public Connection connection;
    private EventLoopGroup workerGroup;

    public Multiplayer() {
        workerGroup = new NioEventLoopGroup();
        new Thread(() -> {
            Bootstrap b = new Bootstrap();
            connection = new Connection(Connection.Role.CLIENT);
            b.group(workerGroup).option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline()
                            .addLast("timeout", new ReadTimeoutHandler(30))
                            .addLast("frame", new FrameDecoder())
                            .addLast("decoder", new PacketDecoder())
                            .addLast("prepender", new LengthFieldPrepender())
                            .addLast("encoder", new PacketEncoder())
                            .addLast("packet_handler", connection);
                }
            })
                    .channel(NioSocketChannel.class)
                    .connect("127.0.0.1", 15151)
                    .syncUninterruptibly();
        }).start();
    }

    public List<Planet> getPlanetList() {
        return planetList;
    }

    public void disconnect() {
        this.connection.send(new ClientDisconnectPacket());
        workerGroup.shutdownGracefully();
    }


    private static class Helper {
        static Multiplayer instance = new Multiplayer();
    }

    public static Multiplayer getInstance() {
        return Helper.instance;
    }
}
