package com.simonhochrein.StrategicCommander.client.gui;

import com.simonhochrein.StrategicCommander.client.Game;
import com.simonhochrein.StrategicCommander.client.input.InputManager;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Input extends GUIElement implements KeyListener, CharListener {
    public String getValue() {
        return message;
    }

    public interface Listener {
        public void onEnter();
    }
    public Listener listener;
    boolean showCursor = false;
    String message = "";
    long lastTime = System.currentTimeMillis();

    public Input() {
        InputManager.getInstance().setListener((KeyListener)this);
        InputManager.getInstance().setListener((CharListener) this);
    }
    @Override
    public void update() {
        if(System.currentTimeMillis() - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            showCursor = !showCursor;
        }
    }

    @Override
    public void render() {
        Vector2f min = CoordinateConverter.screenToGL(new Vector2f(0, 800-20));
        Vector2f max = CoordinateConverter.screenToGL(new Vector2f(800, 800));
        Vector2f cursor = CoordinateConverter.screenToGL(new Vector2f(message.length()*16, 800));
        glBegin(GL_LINES);
        {
            glVertex2f(min.x, min.y);
            glVertex2f(max.x, min.y);
            glVertex2f(max.x, max.y);
            glVertex2f(min.x, max.y);
            glVertex2f(min.x, min.y);
        }
        glEnd();
        Game.getInstance().fontRenderer.drawTextAt(min, message);
        if(showCursor) {
            glBegin(GL_LINES);
            {
                glVertex2f(cursor.x, min.y);
                glVertex2f(cursor.x, max.y);
            }
            glEnd();
        }
    }


    @Override
    public void onKey(int key, int action, int mods) {
        if(key == GLFW_KEY_BACKSPACE && action == GLFW_RELEASE) {
            if(message.length() > 0) {
                message = message.substring(0, message.length() - 1);
            }
        }
        if(key == GLFW_KEY_ENTER && action == GLFW_RELEASE) {
            if(listener != null) {
                listener.onEnter();
            }
        }
    }

    @Override
    public void onChar(char c) {
        message += c;
    }
}
