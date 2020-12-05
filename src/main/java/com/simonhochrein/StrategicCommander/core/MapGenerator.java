package com.simonhochrein.StrategicCommander.core;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MapGenerator {
    private double threshold = 0.7;
    private int height = 20;
    private int width = 20;

    private List<Planet> planetList;

    public MapGenerator() {
        planetList = new ArrayList<>();
    }

    public void Generate() {
        int i = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (Math.random() * Math.random() >= threshold) {
                    Planet p = new Planet(++i, new Vector2f(x, y), NameGenerator.generateName(), -1, false);
                    planetList.add(p);
                }
            }
        }
    }

    public List<Planet> getPlanetList() {
        return planetList;
    }

    public void Debug() {
        List<String> map = new ArrayList<>();
        for (int i = 0; i < height * width; i++) {
            map.add(" ");
        }

        for (Planet planet : planetList) {
            Vector2f p = planet.getPosition();
            map.set((int)p.y * width + (int)p.x, ".");
        }

        for (int i = 0; i < height; i++) {
            System.out.println(String.join("", map.subList(i * width, (i + 1) * width)));
        }
    }
}
