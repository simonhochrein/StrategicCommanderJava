package com.simonhochrein.StrategicCommander.client.input;

import com.simonhochrein.StrategicCommander.client.gui.CharListener;
import com.simonhochrein.StrategicCommander.client.gui.KeyListener;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {

    private long glfwWindow;
    private float x;
    private float y;
    private int width;
    private int height;
    private boolean inWindow;

    private InputManager() {}

    public int getKey(int glfwKey) {
        return glfwGetKey(glfwWindow, glfwKey);
    }

    public void SetWindow(long glfwWindow) {
        this.glfwWindow = glfwWindow;
    }

    public void SetCursorPosition(float x, float y) {
        if(inWindow) {
            this.x = x;
            this.y = y;
        }
    }
    public void SetCursorInWindow(boolean inWindow) {
        this.inWindow = inWindow;
    }

    public void SetWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Vector2f PixelRatio() {
        return new Vector2f(2f/width, 2f/height);
    }

    public Vector2f GetWindowSize() {
        return new Vector2f(width, height);
    }

    public boolean leftMouseDown() {
        return glfwGetMouseButton(glfwWindow, GLFW_MOUSE_BUTTON_LEFT) == 1;
    }
    public Vector2f getCursorPosition() {
        return new Vector2f((x/(float)width*2f)-1f, (y/(float)height*2f)-1f);
    }
    public Vector2f GetCursorPositionScreen() {
        return new Vector2f(x, y);
    }

    private KeyListener keylistener;
    private CharListener charlistener;

    public void setListener(KeyListener listener) {
        this.keylistener = listener;
    }
    public void setListener(CharListener listener) {
        this.charlistener = listener;
    }

    public void keyCallback(long glfwWindow, int key, int scancode, int action, int mods) {
        if(keylistener != null) {
            keylistener.onKey(key, action, mods);
        }
    }
    public void charCallback(long glfwWindow, int key) {
        if(charlistener != null) {
            charlistener.onChar((char)key);
        }
    }

    private static class HelperHolder {
        private static final InputManager instance = new InputManager();
    }

    public static InputManager getInstance() {
        return HelperHolder.instance;
    }
}
