package com.daniel.GameTest;

import com.daniel.GameTest.Datas.SaveScore;
import com.daniel.GameTest.Input.KeyInput;
import com.daniel.GameTest.Input.MouseInput;
import com.daniel.GameTest.objects.Entitys.Blocks;
import com.daniel.GameTest.objects.Spawner;
import com.daniel.GameTest.objects.GameObjectManager;
import com.daniel.GameTest.objects.ID;
import com.daniel.GameTest.objects.Entitys.Player;
import com.daniel.GameTest.objects.UI.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {

    public static int width = 1225, height = 910;

    private Thread thread;
    private boolean running = false;
    private JFrame frame;
    public static GameObjectManager gom = new GameObjectManager();
    private KeyInput input;
    public static Player player = new Player(Game.width / 2, Game.height / 2, ID.Player);
    public static List<Blocks> border = new ArrayList<>();
    public static MouseInput mInput;
    private HUD hud;
    private Spawner spawner;
    public static SaveScore score = new SaveScore("C:\\Users\\Daniel\\Desktop\\score.txt");
    public static boolean started = false;

    public Game() {
        Dimension s = new Dimension(width, height);
        setPreferredSize(s);
        setMaximumSize(s);
        setMinimumSize(s);

        init();
        start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("ShooterSurvival" + "  |  " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void start() {
        running = true;
        thread = new Thread(this, "Main");
        thread.run();
    }

    private void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        input = new KeyInput();
        mInput = new MouseInput();
        spawner = new Spawner();
        hud = new HUD();

        frame = new JFrame("ShooterSurvival");
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addMouseListener(mInput);
        addKeyListener(player.getInput());
        addMouseMotionListener(mInput);

        gom.add(player);

        for(int i = 0; i < width / 35 - 1; i++) {
            gom.add(new Blocks(35 * i, 0, ID.Blocks));
            border.add(new Blocks(35 * i, 0, ID.Blocks));
        }

        for(int i = 0; i < width / 35 - 2; i++) {
            gom.add(new Blocks(35 + 35 * i, height - 35, ID.Blocks));
            border.add(new Blocks(35 + 35 * i, height - 35, ID.Blocks));
        }

        for(int i = 0; i < (height / 35) - 1; i++) {
            gom.add(new Blocks(0, (35 * i) + 35, ID.Blocks));
            border.add(new Blocks(0, 35 * i + 35, ID.Blocks));
        }

        for(int i = 0; i < height / 35; i++) {
            gom.add(new Blocks(width - 35, 35 * i, ID.Blocks));
            border.add(new Blocks(width - 35, 35 * i, ID.Blocks));
        }

        frame.repaint();

    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        if(started) {
            if (!HUD.lost) {
                gom.render(g);
                hud.render(g);
            } else {
                g.setColor(Color.RED);
                g.setFont(new Font("Warwick", Font.BOLD, 25));
                g.drawString("GAME OVER", Game.width / 2 - 75, Game.height / 2);
                g.drawString("Drücke x um das Spiel neuzustarten!", Game.width / 2 - 75, Game.height / 2 - 50);

                try {
                    Thread.sleep(500);
                    renderEnd();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            g.setColor(Color.BLUE);
            g.setFont(new Font("Warwick", Font.BOLD, 25));
            g.drawString("Drücke x um das Spiel zu starten!", Game.width / 2 - 150, Game.height / 2 - 150);
        }

        g.dispose();
        bs.show();
    }

    private void renderEnd() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        score.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        if (!HUD.lost) {
            gom.tick();
            hud.tick();
            spawner.tick();
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
