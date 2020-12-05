package com.simonhochrein.StrategicCommander.client.renderer;

import com.simonhochrein.StrategicCommander.client.input.InputManager;
import org.joml.Vector2f;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class FontRenderer {
    Texture fontTexture;
    float intervalX = 32f / 512f;
    float intervalY = 32f / 512f;
    int fontSize = 16;

    private Vector2f getComputedSize() {
        Vector2f size = InputManager.getInstance().PixelRatio();
        return new Vector2f(fontSize * size.x, fontSize * size.y);
    }

    public FontRenderer(String name) {
        fontTexture = new Texture(name);
    }

    public void drawTextAt(Vector2f position, String text) {
        glPushMatrix();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(position.x, position.y, 0);
        DrawText(text);
        glPopMatrix();
    }

    public void DrawText(String text) {
        fontTexture.bind();
//        glColor4f(1, 1, 1, 1);

        glBegin(GL_QUADS);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            DrawLetter(i, (c % 16), (int) (c / 16));
        }
        glEnd();

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    private void DrawLetter(int offset, int x, int y) {
        Vector2f size = getComputedSize();
        float width = size.x;
        float height = size.y;

        glTexCoord2f(intervalX * x, intervalY * y);
        glVertex2f(offset * width, 0);

        glTexCoord2f(intervalX * (x + 1), intervalY * y);
        glVertex2f((offset + 1) * width, 0);

        glTexCoord2f(intervalX * (x + 1), intervalY * (y + 1));
        glVertex2f((offset + 1) * width, -height);

        glTexCoord2f(intervalX * x, intervalY * (y + 1));
        glVertex2f(offset * width, -height);
    }

    public float getTextHeight() {
        return getComputedSize().y;
    }
    public float getTextWidth(String str) {
        return str.length()*getComputedSize().x;
    }
}
