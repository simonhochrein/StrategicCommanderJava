package com.simonhochrein.StrategicCommander.client.renderer;

import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private ShaderType type;
    private String source;
    private int shaderId = 0;

    public Shader(ShaderType type, String source) {
        this.type = type;
        this.source = source;
    }

    public void Compile() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            shaderId = glCreateShader(type == ShaderType.Fragment ? GL_FRAGMENT_SHADER : GL_VERTEX_SHADER);
            glShaderSource(shaderId, source);
            glCompileShader(shaderId);

            IntBuffer status = stack.callocInt(1);

            glGetShaderiv(shaderId, GL_COMPILE_STATUS, status);

            if(status.get() == GL_FALSE) {
                System.out.println("====================================");
                System.out.print(glGetShaderInfoLog(shaderId));
                System.out.println("====================================");
                throw new Exception("Shader Compilation Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getShaderId() {
        return shaderId;
    }
}
