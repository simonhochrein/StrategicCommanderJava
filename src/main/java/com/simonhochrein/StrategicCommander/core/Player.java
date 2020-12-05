package com.simonhochrein.StrategicCommander.core;

import io.netty.channel.Channel;

import java.util.UUID;

public class Player {
    private Channel channel;

    public int id;
    public String uuid;

    public String name;

    public Player(int id, String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }
}
