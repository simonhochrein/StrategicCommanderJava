package com.simonhochrein.StrategicCommander.client.gui;

import com.simonhochrein.StrategicCommander.client.Game;
import com.simonhochrein.StrategicCommander.client.input.InputManager;
import com.simonhochrein.StrategicCommander.client.renderer.Texture;
import org.joml.Rectanglef;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

enum ButtonState {
    None,
    Pressed,
    Hovered
}


public class Button extends GUIElement {
    private final Rectanglef rectangle;
    private Texture texture;
    private String text;
    private ButtonState state = ButtonState.None;

    public Button(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.rectangle = new Rectanglef(x, y, x + width, y + height);
    }

    public Button(String text, float x, float y, float width, float height) {
        this.text = text;
        this.rectangle = new Rectanglef(x, y, x + width, y + height);
    }

    private ButtonAction action;

    public void setAction(ButtonAction action) {
        this.action = action;
    }

    @Override
    public void update() {
        Vector2f p = InputManager.getInstance().GetCursorPositionScreen();
        if (rectangle.containsPoint(p)) {
            if (InputManager.getInstance().leftMouseDown()) {
                state = ButtonState.Pressed;
            } else {
                if(state == ButtonState.Pressed) {
                    if(this.action != null) {
                        this.action.action();
                    }
                }
                state = ButtonState.Hovered;
            }
        } else {
            state = ButtonState.None;
        }
    }

    @Override
    public void render() {
        Vector2f min = CoordinateConverter.screenToGL(new Vector2f(rectangle.minX, rectangle.minY));
        Vector2f max = CoordinateConverter.screenToGL(new Vector2f(rectangle.maxX, rectangle.maxY));
        float x = min.x;
        float y = min.y;

        glPushMatrix();
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glBindTexture(GL_TEXTURE_2D, 0);

        switch (state) {
            case None:
                glColor3f(1, 0, 1);
                break;
            case Pressed:
                glColor3f(1, 0, 0);
                break;
            case Hovered:
                glColor3f(0, 1, 0);
                break;
        }
        glBegin(GL_QUADS);
//        glTexCoord2f(0, 0);
//        glTexCoord2f(1, 0);
//        glTexCoord2f(1, 1);
//        glTexCoord2f(0, 1);
        glVertex2f(min.x, min.y); // Top Left
        glVertex2f(max.x, min.y); // Top Right
        glVertex2f(max.x, max.y); // Bottom Right
        glVertex2f(min.x, max.y); // Bottom Left
        glEnd();
        glColor3f(1,1,1);
        if (text != null) {
            glTranslatef(x, y, 0);
            Game.getInstance().fontRenderer.DrawText(text);
        }
        glPopMatrix();
    }
}
