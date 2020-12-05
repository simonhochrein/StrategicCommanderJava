package com.simonhochrein.StrategicCommander.client.gui;

import org.joml.Rectanglef;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Slot extends GUIElement {
    private final Rectanglef rectangle;

    public Slot(Vector2f position) {
        this.rectangle = new Rectanglef(position.x, position.y, position.x+40, position.y+40);
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        Vector2f min = CoordinateConverter.screenToGL(new Vector2f(rectangle.minX, rectangle.minY));
        Vector2f max = CoordinateConverter.screenToGL(new Vector2f(rectangle.maxX, rectangle.maxY));

        glBindTexture(GL_TEXTURE_2D, 0);

        glBegin(GL_LINE_STRIP);
        glVertex2f(min.x, min.y);
        glVertex2f(max.x, min.y);
        glVertex2f(max.x, max.y);
        glVertex2f(min.x, max.y);
        glVertex2f(min.x, min.y);
        glEnd();
    }
}
