package com.daniel.GameTest.objects.Entitys;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.Input.KeyInput;
import com.daniel.GameTest.objects.GameObjects;
import com.daniel.GameTest.objects.ID;

import java.awt.*;

public class Player extends GameObjects {

    private KeyInput input;

    public Player(int x, int y, ID id) {
        super(x, y, id);

        velX = 2;
        velY = 2;
        input = new KeyInput();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 24, 24);

        g.setColor(Color.BLACK);
    }

    @Override
    public void tick() {
        collision();
        if (input.isUp()) {
            y -= velY;
        }

        if (input.isDown()) {
            y += velY;
        }

        if (input.isRight()) {
            x += velX;
        }

        if (input.isLeft()) {
            x -= velX;
        }
    }

    private void collision() {
        for (int i = 0; i < Game.border.size(); i++) {
            if (getBounds().intersects(new Rectangle((int) Game.border.get(i).getX(), (int)Game.border.get(i).getY(), 32, 32))) {
                if (Game.border.get(i).getY() == 0) {
                    y = 36;
                }
                if (Game.border.get(i).getY() == Game.height - 35) {
                    y = Game.height - 59;
                }
                if (Game.border.get(i).getX() == 0) {
                    x = 36;
                }
                if (Game.border.get(i).getX() == Game.width - 35) {
                    x = Game.width - 59;
                }
            }
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 24, 24).getBounds();
    }

    public KeyInput getInput() {
        return input;
    }
}
