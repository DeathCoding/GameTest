package com.daniel.GameTest.objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {

    private List<GameObjects> allObjects = new ArrayList<GameObjects>();

    public void render(Graphics g) {
        for (int i = 0; i < allObjects.size(); i++) {
            allObjects.get(i).render(g);
        }
    }

    public void tick() {
        for (int i = 0; i < allObjects.size(); i++) {
            allObjects.get(i).tick();
        }
    }

    public void add(GameObjects go) {
        allObjects.add(go);
    }

    public void remove(GameObjects go) {
        allObjects.remove(go);
    }

    public List<GameObjects> getAllObjects() {
        return allObjects;
    }

}
