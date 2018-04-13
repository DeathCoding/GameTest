package com.daniel.GameTest.objects;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.objects.Entitys.Enemy;
import com.daniel.GameTest.objects.UI.HUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawner {

    public static List<Enemy> allEnemys;
    private HUD hud;
    private static int level;
    private static boolean spwaned = false;
    private Random x, y;

    public Spawner() {
        hud = new HUD();
        allEnemys = new ArrayList<>();

        level = hud.getLevel();
        x = new Random(1226);
        y = new Random(911);
    }

    public void tick() {
        if (!spwaned) {
            for (int i = 0; i < 5 * hud.getLevel(); i++) {
                int xA = x.nextInt(1226), yA = y.nextInt(911);
                allEnemys.add(new Enemy(xA, yA, ID.Enemy));
                Game.gom.add(new Enemy(xA, yA, ID.Enemy));
            }
            setSpwaned(true);
        }
    }

    public static void setSpwaned(boolean spwaned) {
        Spawner.spwaned = spwaned;
    }
}
