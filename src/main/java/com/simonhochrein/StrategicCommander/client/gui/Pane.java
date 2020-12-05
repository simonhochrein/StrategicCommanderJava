package com.simonhochrein.StrategicCommander.client.gui;

import org.joml.Rectanglef;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Pane extends GUIElement {
    private final Rectanglef rectangle;

    public Pane(Rectanglef rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        Vector2f min = CoordinateConverter.screenToGL(new Vector2f(rectangle.minX, rectangle.minY));
        Vector2f max = CoordinateConverter.screenToGL(new Vector2f(rectangle.maxX, rectangle.maxY));
glBindTexture(GL_TEXTURE_2D, 0);
        glColor4f(0, 0, 0, 1);
        glBegin(GL_QUADS);
        glVertex2f(min.x, min.y);
        glVertex2f(max.x, min.y);
        glVertex2f(max.x, max.y);
        glVertex2f(min.x, max.y);
        glEnd();
        glColor4f(1, 1, 1, 1);
    }
}
