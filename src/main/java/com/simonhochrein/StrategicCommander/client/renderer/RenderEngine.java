package com.simonhochrein.StrategicCommander.client.renderer;

import com.simonhochrein.StrategicCommander.client.Scene;
import com.simonhochrein.StrategicCommander.client.input.InputManager;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class RenderEngine {
    private long window;
    private Scene scene;

    public RenderEngine() {
    }

    public void Initialize() {
        InitializeGLFW();
        InitializeGL();
    }

    public void InitializeGL() {
        GL.createCapabilities();
        System.out.println("----------------------------");
        System.out.println("OpenGL Version : " + glGetString(GL_VERSION));
        System.out.println("OpenGL Max Texture Size : " + glGetInteger(GL_MAX_TEXTURE_SIZE));
        System.out.println("OpenGL Vendor : " + glGetString(GL_VENDOR));
        System.out.println("OpenGL Renderer : " + glGetString(GL_RENDERER));
        System.out.println("----------------------------");
    }

    public void InitializeGLFW() {
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);
        glfwWindowHint(GLFW_OPENGL_PROFILE, 0);
        glfwWindowHint(GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW_TRUE);

        // Create the window
        window = glfwCreateWindow(800, 800, "Hello World!", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        InputManager.getInstance().SetWindow(window);
        InputManager.getInstance().SetWindowSize(800, 800);

        glfwSetWindowSizeCallback(window, (window, width, height) -> {
           InputManager.getInstance().SetWindowSize(width, height);
        });

        glfwSetCursorEnterCallback(window, (window, inWindow) -> {
            InputManager.getInstance().SetCursorInWindow(inWindow);
        });

        glfwSetCursorPosCallback(window, (window, x, y) -> {
            InputManager.getInstance().SetCursorPosition((float)x, (float)y);
        });

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            InputManager.getInstance().keyCallback(window, key, scancode, action, mods);
        });
        glfwSetCharCallback(window, (window, key) -> {
            InputManager.getInstance().charCallback(window, key);
        });

        glfwSetWindowCloseCallback(window, (window) -> {
            System.out.println("Close");
            glfwSetWindowShouldClose(window, true);
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    public boolean ShouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void Update() {
        scene.update();
    }

    public void Render() {
        scene.render();
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void LoadScene(Scene scene) {
        this.scene = scene;
    }

    public void Terminate() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
