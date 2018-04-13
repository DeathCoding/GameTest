package com.daniel.GameTest.objects.Entitys;

import com.daniel.GameTest.objects.GameObjects;
import com.daniel.GameTest.objects.ID;

import java.awt.*;

public class Blocks extends GameObjects {

    public Blocks(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    @Override
    public void tick() {}

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
