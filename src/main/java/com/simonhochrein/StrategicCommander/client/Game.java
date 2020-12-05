package com.simonhochrein.StrategicCommander.client;

import com.simonhochrein.StrategicCommander.client.input.InputManager;
import com.simonhochrein.StrategicCommander.client.multiplayer.Multiplayer;
import com.simonhochrein.StrategicCommander.client.renderer.FontRenderer;
import com.simonhochrein.StrategicCommander.client.renderer.RenderEngine;
import com.simonhochrein.StrategicCommander.client.scenes.GameScene;
import com.simonhochrein.StrategicCommander.client.scenes.MainMenu;
import com.simonhochrein.StrategicCommander.client.sound.SoundEngine;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Game {
    public RenderEngine renderEngine;
    public FontRenderer fontRenderer;
//    SoundEngine soundEngine;

    void Initialize() {
        renderEngine = new RenderEngine();
//        soundEngine = new SoundEngine();

        renderEngine.Initialize();
        fontRenderer = new FontRenderer("/Users/simonhochrein/IdeaProjects/StrategicCommander/src/main/resources/Font2.png");
        renderEngine.LoadScene(new GameScene());
    }
    public int frameRate = 0;

    void Loop() {
        double previousTime = glfwGetTime();
        int frameCount = 0;
//        soundEngine.Play();
        while(!renderEngine.ShouldClose()) {
            double currentTime = glfwGetTime();
            frameCount++;

            if(currentTime - previousTime >= 1.0) {
                frameRate = frameCount;
                frameCount = 0;
                previousTime = currentTime;
            }

            renderEngine.Update();
            renderEngine.Render();
        }
    }
    void Terminate() {
        Multiplayer.getInstance().disconnect();
        renderEngine.Terminate();
    }

    public void Run() {
        Initialize();
        Loop();
        Terminate();
    }

    private static class Helper {
        public static Game game = new Game();
    }
    public static Game getInstance() {
        return Helper.game;
    }
}
