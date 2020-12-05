package com.simonhochrein.StrategicCommander.client.renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

public class Camera {
    private Vector3f position;

    public Camera(Vector3f position) {
        this.position = position;
    }

    public Matrix4f getMatrix() {
        return new Matrix4f().setPerspective((float)Math.toRadians(70), 1,  0.01f, 100f).lookAt(position, new Vector3f(0, -1, -1).add(position), new Vector3f(0, 1, 0));
    }

    public Matrix4f getWorldMatrix() {
        return new Matrix4f().translate(position.negate());
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }
}
