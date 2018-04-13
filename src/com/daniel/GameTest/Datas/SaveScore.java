package com.daniel.GameTest.Datas;

import com.daniel.GameTest.Game;
import com.daniel.GameTest.objects.UI.HUD;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class SaveScore {

    private File f;
    Scanner s;
    OutputStream os;
    StringBuilder sb;

    public SaveScore(String path) {
        f = new File(path);
        sb = new StringBuilder();
        try {
            s = new Scanner(f);
            os = new FileOutputStream(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write() {
        sb.append(HUD.getScore());
        try {
            os.write(sb.toString().getBytes());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return Integer.parseInt(s.nextLine());
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Warwick", Font.BOLD, 15));
        g.drawString("Score: " + HUD.getScore(), Game.width / 2 - 75, Game.height / 2 + 30);
    }
}
