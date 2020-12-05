package com.simonhochrein.StrategicCommander.client.renderer.testing;

import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Cube {
    public static void Render(float r) {
        glBegin(GL_QUADS);

        // Front Face
        glColor4f(1, 0, 0, 1f);
        glVertex3f(-r, -r, -r);
        glVertex3f(r, -r, -r);
        glVertex3f(r, r, -r);
        glVertex3f(-r, r, -r);

        // Back Face
        glColor4f(0, 1, 0, 1f);
        glVertex3f(-r, -r, r);
        glVertex3f(r, -r, r);
        glVertex3f(r, r, r);
        glVertex3f(-r, r, r);

        // Bottom Face
        glColor4f(1, 1, 0, 1f);
        glVertex3f(-r, -r, -r);
        glVertex3f(r, -r, -r);
        glVertex3f(r, -r, r);
        glVertex3f(-r, -r, r);

        // Top Face
        glColor4f(0, 0, 1, 1f);
        glVertex3f(-r, r, -r);
        glVertex3f(r, r, -r);
        glVertex3f(r, r, r);
        glVertex3f(-r, r, r);

        // Left Face
        glColor4f(1, 0, 1, 1f);
        glVertex3f(-r, -r, -r);
        glVertex3f(-r, r, -r);
        glVertex3f(-r, r, r);
        glVertex3f(-r, -r, r);

        // Top Face
        glColor4f(0, 1, 1, 1f);
        glVertex3f(r, -r, -r);
        glVertex3f(r, r, -r);
        glVertex3f(r, r, r);
        glVertex3f(r, -r, r);

        glEnd();
    }
}
