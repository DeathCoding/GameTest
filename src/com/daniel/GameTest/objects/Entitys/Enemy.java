package com.daniel.GameTest.objects.Entitys;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.Input.MouseInput;
import com.daniel.GameTest.objects.GameObjects;
import com.daniel.GameTest.objects.ID;
import com.daniel.GameTest.objects.Spawner;
import com.daniel.GameTest.objects.UI.HUD;

import java.awt.*;

public class Enemy extends GameObjects {

    public Enemy(int x, int y, ID id) {
        super(x, y, id);

        velX = 2;
        velY = 2;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int)y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - Game.player.getX() - 16;
        float diffY = y - Game.player.getY() - 16;
        float distance = (float) Math.sqrt((x - Game.player.getX()) * (x - Game.player.getX()) + (y - Game.player.getY()) * (y - Game.player.getY()));

        velX = (float) ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);

        collision();

    }

    private void collision() {
        if (getBounds().intersects(new Rectangle((int) Game.player.getX(), (int) Game.player.getY(), 24, 24))) {
            if (HUD.playerHEALTH == 0) {
                return;
            }
            HUD.playerHEALTH--;
        }

        for (int j = 0; j < Spawner.allEnemys.size(); j++) {
            for (int i = 0; i < MouseInput.allBullets.size(); i++) {
                if (getBounds().intersects(new Rectangle((int) MouseInput.allBullets.get(i).getX(), (int) MouseInput.allBullets.get(i).getY(), 8, 8))) {
                    Spawner.allEnemys.remove(j);
                    Game.gom.remove(this);
                    Game.gom.remove(MouseInput.allBullets.get(i));
                    MouseInput.allBullets.remove(i);

                    HUD.setScore(50);

                }
            }
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16).getBounds();
    }

}
