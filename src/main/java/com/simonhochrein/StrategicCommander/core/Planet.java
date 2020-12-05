package com.simonhochrein.StrategicCommander.core;

import org.joml.Vector2f;

import java.util.UUID;

public class Planet {
    private final int id;
    private final String name;
    private boolean homeworld;
    private int ownerId;
    private Vector2f position;

    public Planet(int id, Vector2f position, String name, int ownerId, boolean homeworld) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.ownerId = ownerId;
        this.homeworld = homeworld;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwner(Player owner) {
        if(homeworld) {
            homeworld = false;
        }
        this.ownerId = owner.id;
    }

    public boolean isHomeworld() {
        return homeworld;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

//    public String getName() {
//        return name;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
