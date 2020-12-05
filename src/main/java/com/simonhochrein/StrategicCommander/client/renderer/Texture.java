package com.simonhochrein.StrategicCommander.client.renderer;

import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private int textureId = 0;
    public Texture(String path) {
        ByteBuffer buffer;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            String texture = path;

            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);
            glPixelStorei(GL_UNPACK_ALIGNMENT, textureId);
            buffer = stbi_load(texture, w, h, channels, 4);
            if(buffer ==null) {
                throw new Exception("Can't load file "+texture+" "+stbi_failure_reason());
            }
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(),  0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,  GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,  GL_LINEAR);

            stbi_image_free(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTextureId() {
        return textureId;
    }

    public void bind() {
//        System.out.println(glIsEnabled(GL_TEXTURE_2D));
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public void dispose() {
        glDeleteTextures(textureId);
    }

    public final void unbind() {
//        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
