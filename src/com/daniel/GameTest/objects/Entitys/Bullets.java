package com.daniel.GameTest.objects.Entitys;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.Input.MouseInput;
import com.daniel.GameTest.objects.GameObjects;
import com.daniel.GameTest.objects.ID;

import java.awt.*;

public class Bullets extends GameObjects {

    int range = 50;
    float nx, ny;
    float dx, dy, dir;

    public Bullets(int x, int y, ID id) {
        super(x, y, id);

        dx = (float) Game.mInput.getX() - (float) Game.width / 2;
        dy = (float)Game.mInput.getY() - (float)Game.height / 2;
        double dir = Math.atan2(dy, dx);
        nx = (float)(3 * Math.cos(dir));
        ny = (float)(3 * Math.sin(dir));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 8, 8);
    }

    @Override
    public void tick() {
        collision();
        x += nx;
        y += ny;

        if (x < 0 - 16 || x > Game.width + 16 || y < 0 - 16 || y > Game.height + 16) {
            Game.gom.remove(this);
            MouseInput.allBullets.remove(this);
        }

    }

    private void collision() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8, 8).getBounds();
    }

    public MouseInput getMouseInput() {
        return Game.mInput;
    }
}
