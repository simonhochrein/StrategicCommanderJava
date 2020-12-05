package com.simonhochrein.StrategicCommander.client.scenes;

import com.simonhochrein.StrategicCommander.client.Scene;
import com.simonhochrein.StrategicCommander.client.gui.Button;
import com.simonhochrein.StrategicCommander.client.gui.GUIEngine;
import com.simonhochrein.StrategicCommander.client.renderer.Texture;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu extends Scene {
    Texture texture;
    GUIEngine guiEngine;
    public MainMenu() {
        guiEngine = new GUIEngine();
        texture = new Texture("Zhong.png");
        guiEngine.addElement(new Button(texture, -0.4f, 0, 0.8f, 0.4f));
        guiEngine.addElement(new Button(texture, -0.4f, -0.5f, 0.8f, 0.4f));
    }

    @Override
    public void update() {
        guiEngine.update();
//        InputManager.CheckForInput();
    }

    @Override
    public void render() {
        glClearColor(0, 0, 0, 1);
        glClear(GL_COLOR_BUFFER_BIT);
        guiEngine.render();
    }
}
