package com.simonhochrein.StrategicCommander.client.scenes;

import com.simonhochrein.StrategicCommander.client.Game;
import com.simonhochrein.StrategicCommander.client.Scene;
import com.simonhochrein.StrategicCommander.client.gui.*;
import com.simonhochrein.StrategicCommander.client.input.InputManager;
import com.simonhochrein.StrategicCommander.client.multiplayer.Multiplayer;
import com.simonhochrein.StrategicCommander.client.renderer.Camera;
import com.simonhochrein.StrategicCommander.client.renderer.FontRenderer;
import com.simonhochrein.StrategicCommander.client.renderer.Mesh;
import com.simonhochrein.StrategicCommander.client.renderer.Texture;
import com.simonhochrein.StrategicCommander.client.util.Raycast;
import com.simonhochrein.StrategicCommander.core.Planet;
import com.simonhochrein.StrategicCommander.network.packets.ChatNewMessagePacket;
import com.simonhochrein.StrategicCommander.network.packets.SendShipsPacket;
import org.joml.*;
import org.joml.Math;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.Optional;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameScene extends Scene {

    Mesh m;
    Texture texture;
    Texture texture2;
    GUIEngine engine;
    int selectedPlanet = -1;

    boolean chatOpen = false;

    public GameScene() {
        m = new Mesh("/Users/simonhochrein/Downloads/untitled.obj");
        texture = new Texture("/Users/simonhochrein/Downloads/Background 2.png");
        texture2 = new Texture("/Users/simonhochrein/Downloads/Zhong.png");


        glEnable(GL_TEXTURE_2D);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        c = new Camera(new Vector3f(10, 10, 15));

        engine = new GUIEngine();
    }

    public boolean selectingPlanet = false;

    public void makeChatGUI() {
        engine.clear();
        Pane p = new Pane(new Rectanglef(0, 0, 800, 800));
        Input i = new Input();
        i.listener = () -> {
            Multiplayer.getInstance().connection.send(new ChatNewMessagePacket(i.getValue()));
            chatOpen = false;
        };
        engine.addElements(p, i);
    }

    public void makeGUI() {
        engine.clear();
        Optional<Planet> planet = Multiplayer.getInstance().planetList.stream().filter(p -> p.getId() == selectedPlanet).findFirst();
        if (planet.isPresent()) {
            Label label = new Label(new Vector2f(400, 40), planet.get().getName());
            Pane p = new Pane(new Rectanglef(0, 0, 800, 800));
            Image image = new Image(texture2, new Rectanglef(40, 40, 200, 200));
            Button sendShips = new Button("Send Ships", 20, 220, 200, 20);
            sendShips.setAction(() -> {
                selectingPlanet = true;
                Multiplayer.getInstance().connection.send(new SendShipsPacket(0, 1, 2));
            });

            Button close = new Button("Close", 0, 0, 100, 20);
            close.setAction(() -> {
                selectedPlanet = -1;
            });

            engine.addElements(p, image, label, close, sendShips);

            for (int i = 0; i < 5; i++) {
                Slot slot = new Slot(new Vector2f(i * 50 + 405, 80));
                engine.addElement(slot);
            }
        }
    }

    final Camera c;

    long animationStart = System.currentTimeMillis();

    double animationPos;

    @Override
    public void update() {
        if (selectedPlanet > -1 || chatOpen) {
            engine.update();
        }
        long ms = System.currentTimeMillis();

        if (ms - animationStart < 4000) {
            float progress = Math.clamp((ms - animationStart) / 4000f, 0, 1);
            animationPos = progress;
        } else {
            animationStart = ms;
            animationPos = 0;
        }

        if (chatOpen == false) {
            if (InputManager.getInstance().getKey(GLFW_KEY_W) == GLFW_PRESS) {
                c.setPosition(c.getPosition().add(new Vector3f(0, 0, -0.5f)));
            }
            if (InputManager.getInstance().getKey(GLFW_KEY_S) == GLFW_PRESS) {
                c.setPosition(c.getPosition().add(new Vector3f(0, 0, 0.5f)));
            }
            if (InputManager.getInstance().getKey(GLFW_KEY_A) == GLFW_PRESS) {
                c.setPosition(c.getPosition().add(new Vector3f(-0.5f, 0, 0)));
            }
            if (InputManager.getInstance().getKey(GLFW_KEY_D) == GLFW_PRESS) {
                c.setPosition(c.getPosition().add(new Vector3f(0.5f, 0, 0)));
            }
            if (InputManager.getInstance().getKey(GLFW_KEY_T) == GLFW_PRESS) {
                chatOpen = true;
                makeChatGUI();
            }
        }
    }

    //    private int deg = 0;
    FontRenderer fontRenderer = Game.getInstance().fontRenderer;

    @Override
    public void render() {
        glClearColor(0, 0, 0, 0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer matrix = stack.mallocFloat(16);
            Matrix4f perspective = c.getMatrix();

            Vector2f position = InputManager.getInstance().getCursorPosition();
            Raycast rc = new Raycast(c, position);

            Planet hit = null;

            glPushMatrix();
            glMatrixMode(GL_PROJECTION);
            glLoadMatrixf(perspective.get(matrix));

            for (Planet p : Multiplayer.getInstance().getPlanetList()) {
                glPushMatrix();
                glMatrixMode(GL_MODELVIEW);
                Matrix4f model = new Matrix4f().identity().translate(p.getPosition().x, 0, p.getPosition().y).scale(0.3f);

                model.get(matrix);
                glLoadMatrixf(matrix);
//                if (p.getId() == selectedPlanet) {
//                    Cube.Render(1);
//                } else {
                texture.bind();
                m.Render();
//                }

                glPopMatrix();

                if (selectedPlanet == -1 && !chatOpen) {
                    if (rc.hit(new Vector3f(p.getPosition().x, 0, p.getPosition().y), new Vector3f(0.3f, 0.3f, 0.3f))) {
                        hit = p;
                        if (InputManager.getInstance().leftMouseDown()) {
                            if (selectedPlanet != p.getId()) {
                                selectedPlanet = p.getId();
                                makeGUI();
                            }
                        }
                    }
                }
            }


            glPushMatrix();

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            if (hit != null) {
                Vector2f pos = InputManager.getInstance().getCursorPosition();
                float width = fontRenderer.getTextWidth(hit.getName());
                float height = fontRenderer.getTextHeight();
                pos.y = -pos.y;
                pos.x += 0.03f;
                glDisable(GL_DEPTH_TEST);
                glBegin(GL_QUADS);
//                glColor3f(1, 0, 0);
                glVertex2f(pos.x, pos.y);
                glVertex2f(pos.x + width, pos.y);
                glVertex2f(pos.x + width, pos.y - height);
                glVertex2f(pos.x, pos.y - height);
                glEnd();
                fontRenderer.drawTextAt(pos, hit.getName());
                glEnable(GL_DEPTH_TEST);
            }
            String selection = "Selected Planet: " + selectedPlanet;
            String fps = Game.getInstance().frameRate + " FPS";
            fontRenderer.drawTextAt(new Vector2f(1 - fontRenderer.getTextWidth(fps), 1), fps);
            fontRenderer.drawTextAt(new Vector2f(1 - fontRenderer.getTextWidth(selection), 1 - fontRenderer.getTextHeight()), selection);

            fontRenderer.drawTextAt(new Vector2f(-1, 1), "Strategic Commander Alpha");
            fontRenderer.drawTextAt(new Vector2f(-1, 1 - fontRenderer.getTextHeight()), "Memory Usage " + (Runtime.getRuntime().freeMemory() / Runtime.getRuntime().totalMemory()) * 100 + "%");
            glPopMatrix();
            if (selectedPlanet > -1 || chatOpen) {
                engine.render();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
