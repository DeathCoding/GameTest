package com.daniel.GameTest.objects.UI;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.objects.Spawner;

import java.awt.*;

public class HUD  {

    public static int playerHEALTH = 100;

    private static int level = 1;
    private static int score = 0;

    public static boolean lost = false;

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((int) Game.player.getX(), (int) Game.player.getY() - 10, 24, 2 );
        g.setColor(new Color(75, 255, 0));
        g.fillRect((int) Game.player.getX(), (int) Game.player.getY() - 10, (playerHEALTH / 4), 2 );

        g.setColor(Color.white);

        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("Level: " + level, 50, 55);
        g.drawString("Score: " + score, 50, 65);

        if (playerHEALTH == 0) {
            lost = true;
            Game.score.write();
        }

    }

    public void tick() {
        if (Spawner.allEnemys.size() == 0) {
            Spawner.setSpwaned(false);
            level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public static void setScore(int Sscore) {
        score += Sscore;
    }

    public static int getScore() {
        return score;
    }
}
