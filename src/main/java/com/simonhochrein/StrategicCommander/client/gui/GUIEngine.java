package com.simonhochrein.StrategicCommander.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GUIEngine {
    private final List<GUIElement> elementList;

    public GUIEngine() {
        elementList = new ArrayList<GUIElement>();
    }

    public void addElement(GUIElement element) {
        elementList.add(element);
    }
    public void addElements(GUIElement ...elements) {
        elementList.addAll(Arrays.asList(elements));
    }

    public void render() {
        glDisable(GL_DEPTH_TEST);
        glPushMatrix();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        for (GUIElement el: elementList
             ) {
            el.render();
        }

        glPopMatrix();
        glEnable(GL_DEPTH_TEST);
    }

    public void update() {
        for (GUIElement el: elementList
        ) {
            el.update();
        }
    }

    public void clear() {
        elementList.clear();
    }
}
