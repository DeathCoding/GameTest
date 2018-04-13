package com.daniel.GameTest.Input;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.objects.Entitys.Bullets;
import com.daniel.GameTest.objects.ID;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInput implements MouseListener, MouseMotionListener {

    private boolean shooted;
    private int x, y;

    private Bullets go;

    public static ArrayList<Bullets> allBullets = new ArrayList<>();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            go = new Bullets((int) Game.player.getX() + 8, (int) Game.player.getY() + 8, ID.Bullets);
            shooted = true;
            Game.gom.add(go);
            allBullets.add(go);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isShooted() {
        return shooted;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public Bullets getGo() {
        return go;
    }
}
