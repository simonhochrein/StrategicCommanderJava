package com.simonhochrein.StrategicCommander.client.gui;

import com.simonhochrein.StrategicCommander.client.input.InputManager;
import org.joml.Vector2f;

public class CoordinateConverter {
    public static Vector2f screenToGL(Vector2f pixel) {
        Vector2f ratio = InputManager.getInstance().PixelRatio();
        return new Vector2f((pixel.x * ratio.x) - 1, 1 - (pixel.y * ratio.y));
    }
}
