package com.simonhochrein.StrategicCommander.client.renderer;

import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Program {
    private Shader vertexShader;
    private Shader fragmentShader;
    private int programId = 0;

    public Program(Shader vertexShader, Shader fragmentShader) {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }

    public void Link() {
        try (MemoryStack stack = MemoryStack.stackPush()){
            programId = glCreateProgram();

            glAttachShader(programId, vertexShader.getShaderId());
            glAttachShader(programId, fragmentShader.getShaderId());

            glLinkProgram(programId);

            IntBuffer status = stack.callocInt(1);

            glGetProgramiv(programId, GL_LINK_STATUS, status);

            if(status.get() == GL_FALSE) {
                System.out.println("====================================");
                System.out.print(glGetProgramInfoLog(programId));
                System.out.println("====================================");
                throw new Exception("Program Link Error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Use() {
        glUseProgram(programId);
    }

    public static void Reset() {
        glUseProgram(0);
    }
}
