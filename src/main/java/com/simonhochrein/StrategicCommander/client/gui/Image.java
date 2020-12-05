package com.simonhochrein.StrategicCommander.client.gui;

import com.simonhochrein.StrategicCommander.client.renderer.Texture;
import org.joml.Rectanglef;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Image extends GUIElement {
    private final Texture texture;
    private final Rectanglef rectangle;

    public Image(Texture texture, Rectanglef rectangle) {
        this.texture = texture;
        this.rectangle = rectangle;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
//        glEnable(GL_TEXTURE_2D);
        Vector2f min = CoordinateConverter.screenToGL(new Vector2f(rectangle.minX, rectangle.minY));
        Vector2f max = CoordinateConverter.screenToGL(new Vector2f(rectangle.maxX, rectangle.maxY));

//        glColor3f(1, 0, 0);
        texture.bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(min.x, min.y);

        glTexCoord2f(0, 1);
        glVertex2f(max.x, min.y);

        glTexCoord2f(1, 1);
        glVertex2f(max.x, max.y);

        glTexCoord2f(1, 0);
        glVertex2f(min.x, max.y);

        glEnd();

//        texture.unbind();
//        glDisable(GL_TEXTURE_2D);
    }
}
