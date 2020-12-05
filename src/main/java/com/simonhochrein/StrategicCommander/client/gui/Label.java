package com.simonhochrein.StrategicCommander.client.gui;

import com.simonhochrein.StrategicCommander.client.Game;
import org.joml.Vector2f;

public class Label extends GUIElement {
    private final Vector2f position;
    private final String text;

    public Label(Vector2f position, String text) {
        this.position = position;
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        Vector2f point = CoordinateConverter.screenToGL(position);
        Game.getInstance().fontRenderer.drawTextAt(point, text);
    }
}