package com.simonhochrein.StrategicCommander.server.main;

import com.simonhochrein.StrategicCommander.core.MapGenerator;
import com.simonhochrein.StrategicCommander.core.Planet;
import com.simonhochrein.StrategicCommander.core.Player;
import com.simonhochrein.StrategicCommander.network.*;
import com.simonhochrein.StrategicCommander.network.packets.ChatMessagePacket;
import com.simonhochrein.StrategicCommander.network.packets.KeepAlivePingPacket;
import com.simonhochrein.StrategicCommander.network.packets.MapPacket;
import com.simonhochrein.StrategicCommander.server.Chat;
import com.simonhochrein.StrategicCommander.server.gui.GUI;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUI gui = new GUI();
        gui.start();
//        SwingUtilities.invokeLater(() -> );
        EventLoopGroup mainGroup = new NioEventLoopGroup();

        MapGenerator map = new MapGenerator();

        map.Generate();

        map.Debug();

        try {
            List<Connection> connectionList = new ArrayList<>();

            Chat.getInstance().addListener((userId, message) -> {
                for(Connection connection : connectionList) {
                    connection.send(new ChatMessagePacket(userId, message));
                }
            });

            ServerBootstrap b = new ServerBootstrap();
            ChannelFuture future = b.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel) throws Exception {
                    Connection c = new Connection(Connection.Role.SERVER);
                    gui.addConnection(c);
                    connectionList.add(c);
                    socketChannel.pipeline()
                            .addLast("timeout", new ReadTimeoutHandler(30))
                            .addLast("frame", new FrameDecoder())
                            .addLast("decoder", new PacketDecoder())
                            .addLast("prepender", new LengthFieldPrepender())
                            .addLast("encoder", new PacketEncoder())
                            .addLast("packet_handler", c);
                    socketChannel.writeAndFlush(new MapPacket(map.getPlanetList()));
                }
            }).group(mainGroup).localAddress("127.0.0.1", 15151).bind();
            new Thread(() -> {
                while (true) {
                    try {
                        List<Connection> disconnectable = new ArrayList<>();

                        for (Connection c : connectionList) {
                            long now = System.currentTimeMillis();

                            if(!c.isOpen() || c.keepAlivePending) {
                                c.disconnect();
                                disconnectable.add(c);
                            } else {
                                c.keepAlivePending = true;
                                c.keepAliveLastRequest = now;
                                c.send(new KeepAlivePingPacket(c.keepAliveLastRequest));
                            }
                        }
                        for(Connection c : disconnectable) {
                            gui.removeConnection(c);
                            connectionList.remove(c);
                        }
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            future.syncUninterruptibly();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            workerGroup.shutdownGracefully();
//            mainGroup.shutdownGracefully();
        }
    }
}
