package com.daniel.GameTest.Input;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.objects.UI.HUD;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean up = false, down = false, left = false, right = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            if (!Game.started) {
                Game.started = true;
            }

            if (HUD.lost) {
                HUD.lost = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
