package com.simonhochrein.StrategicCommander.client.util;

import com.simonhochrein.StrategicCommander.client.input.InputManager;
import com.simonhochrein.StrategicCommander.client.renderer.Camera;
import org.joml.Intersectionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import org.joml.*;

public class Raycast {

    Vector3f origin = new Vector3f();
    Vector3f dir = new Vector3f();
    Camera camera;

    public Raycast(Camera camera, Vector2f position) {
        this.camera = camera;
        camera.getMatrix().unprojectRay(((float) position.x + 1) / 2, ((float) -position.y + 1) / 2, new int[]{0, 0, 1, 1}, origin, dir);
    }

    public boolean hit(Vector3f obj, Vector3f size) {
        dir.normalize();
        Vector2f hit = new Vector2f();
        return Intersectionf.intersectRayAab(camera.getPosition(), dir, new Vector3f(obj).sub(size), new Vector3f(obj).add(size), hit);
    }
}
